package omnihealth.api.domain.doctor;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import omnihealth.api.domain.address.AddressData;
import omnihealth.api.domain.appointment.Appointment;
import omnihealth.api.domain.patient.Patient;
import omnihealth.api.domain.patient.PatientRegistrationData;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DoctorRepositoryTest {

	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private TestEntityManager em;

	@Test
	@DisplayName("should return null when the only registered doctor is not available on the date")
	void testChooseRandomDoctorFreeOnDateScene1() {
		var nextMondayAt10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

		var doctor = registerDoctor("doctor", "doctor@omni.health", "123456", Specialty.CARDIOLOGY);
		var patient = registerPatient("patient", "patient@omni.health", "00000000000");
		registerAppointment(doctor, patient, nextMondayAt10);

		var freeDoctor = doctorRepository.chooseRandomDoctorFreeOnDate(Specialty.CARDIOLOGY, nextMondayAt10);
		assertThat(freeDoctor).isNull();
	}

	@Test
	@DisplayName("should return the doctor when he is available on the date")
	void testChooseRandomDoctorFreeOnDateScene2() {
		var nextMondayAt10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

		var doctor = registerDoctor("doctor", "doctor@omni.health", "123456", Specialty.CARDIOLOGY);

		var freeDoctor = doctorRepository.chooseRandomDoctorFreeOnDate(Specialty.CARDIOLOGY, nextMondayAt10);
		assertThat(freeDoctor).isEqualTo(doctor);
	}

	private void registerAppointment(Doctor doctor, Patient patient, LocalDateTime date) {
		em.persist(new Appointment(null, doctor, patient, date, true, null));
	}

	private Doctor registerDoctor(String name, String email, String crm, Specialty specialty) {
		var doctor = new Doctor(doctorData(name, email, crm, specialty));
		em.persist(doctor);
		return doctor;
	}

	private Patient registerPatient(String name, String email, String cpf) {
		var patient = new Patient(patientData(name, email, cpf));
		em.persist(patient);
		return patient;
	}

	private DoctorRegistrationData doctorData(String name, String email, String crm, Specialty specialty) {
		return new DoctorRegistrationData(name, email, "81999999999", crm, specialty, addressData());
	}

	private PatientRegistrationData patientData(String name, String email, String cpf) {
		return new PatientRegistrationData(name, email, "81999999999", cpf, addressData());
	}

	private AddressData addressData() {
		return new AddressData("xpto street", "neighborhood", "00000000", "Caruaru", "PE", null, null);

	}

}
