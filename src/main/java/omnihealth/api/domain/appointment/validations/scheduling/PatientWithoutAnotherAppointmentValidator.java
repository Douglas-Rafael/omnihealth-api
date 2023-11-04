package omnihealth.api.domain.appointment.validations.scheduling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import omnihealth.api.domain.ExceptionValidation;
import omnihealth.api.domain.appointment.AppointmentRepository;
import omnihealth.api.domain.appointment.AppointmentSchedulingData;

@Component
public class PatientWithoutAnotherAppointmentValidator implements AppointmentScheduleValidator {

	@Autowired
	private AppointmentRepository repository;

	public void validate(AppointmentSchedulingData data) {

		var firstTime = data.date().withHour(7);
		var lastTime = data.date().withHour(18);
		var patientAlreadyHasAnAppointmentScheduledThatDay = repository
				.existsByPatientIdAndDateBetween(data.patientId(), firstTime, lastTime);
		if (patientAlreadyHasAnAppointmentScheduledThatDay) {
			throw new ExceptionValidation("Patient already has an appointment scheduled that day!");
		}
	}
}
