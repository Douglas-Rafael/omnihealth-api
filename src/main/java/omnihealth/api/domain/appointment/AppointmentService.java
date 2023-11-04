package omnihealth.api.domain.appointment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import omnihealth.api.domain.ExceptionValidation;
import omnihealth.api.domain.appointment.validations.cancellation.AppointmentCancellationValidator;
import omnihealth.api.domain.appointment.validations.scheduling.AppointmentScheduleValidator;
import omnihealth.api.domain.doctor.Doctor;
import omnihealth.api.domain.doctor.DoctorRepository;
import omnihealth.api.domain.patient.PatientRepository;

@Service
public class AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private List<AppointmentScheduleValidator> validators;

	@Autowired
	private List<AppointmentCancellationValidator> cancellationValidators;

	public AppointmentDetailData toSchedule(AppointmentSchedulingData data) {
		if (!patientRepository.existsById(data.patientId())) {
			throw new ExceptionValidation("Informed patient id does not exist");
		}

		if (data.doctorId() != null && !doctorRepository.existsById(data.doctorId())) {
			throw new ExceptionValidation("Informed doctor id does not exist");
		}

		validators.forEach(v -> v.validate(data));

		var doctor = chooseDoctor(data);
		if (doctor == null) {
			throw new ExceptionValidation("don't exist doctor ");
		}
		var patient = patientRepository.getReferenceById(data.patientId());
		var appointment = new Appointment(null, doctor, patient, data.date(), true, null);
		appointmentRepository.save(appointment);

		return new AppointmentDetailData(appointment);
	}

	private Doctor chooseDoctor(AppointmentSchedulingData data) {
		if (data.doctorId() != null) {
			return doctorRepository.getReferenceById(data.doctorId());
		}

		if (data.specialty() == null) {
			throw new ExceptionValidation("There is no doctor available on that date!");
		}

		return doctorRepository.chooseRandomDoctorFreeOnDate(data.specialty(), data.date());

	}

	public void deleteAppointment(AppointmentDeleteData data) {

		if (!appointmentRepository.existsById(data.id())) {
			throw new ExceptionValidation("Informed appointment id does not exist!");
		}

		cancellationValidators.forEach(v -> v.validate(data));

		var appointment = appointmentRepository.getReferenceById(data.id());
		appointment.delete(data.reason());

	}

}
