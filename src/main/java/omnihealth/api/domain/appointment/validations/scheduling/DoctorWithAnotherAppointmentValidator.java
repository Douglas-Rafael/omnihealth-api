package omnihealth.api.domain.appointment.validations.scheduling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import omnihealth.api.domain.ExceptionValidation;
import omnihealth.api.domain.appointment.AppointmentRepository;
import omnihealth.api.domain.appointment.AppointmentSchedulingData;

@Component
public class DoctorWithAnotherAppointmentValidator implements AppointmentScheduleValidator {

	@Autowired
	private AppointmentRepository repository;

	public void validate(AppointmentSchedulingData data) {
		var doctorHasAnotherAppointmentAtTheSameTime = repository
				.existsByDoctorIdAndDateAndActiveIsTrue(data.doctorId(), data.date());
		if (doctorHasAnotherAppointmentAtTheSameTime) {
			throw new ExceptionValidation("The doctor already has another appointment scheduled at the same time.");
		}
	}
}
