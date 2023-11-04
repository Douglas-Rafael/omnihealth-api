package omnihealth.api.domain.appointment;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	Boolean existsByDoctorIdAndDateAndActiveIsTrue(Long doctorId, LocalDateTime date);

	Boolean existsByPatientIdAndDateBetween(Long patientId, LocalDateTime firstTime, LocalDateTime lastTime);

	Boolean findActiveById(Long id);

}
