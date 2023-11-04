package omnihealth.api.domain.appointment.validations.scheduling;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import omnihealth.api.domain.ExceptionValidation;
import omnihealth.api.domain.appointment.AppointmentSchedulingData;

@Component("AdvanceTimeSchedulingValidator")
public class AdvanceTimeValidator implements AppointmentScheduleValidator {

	public void validate(AppointmentSchedulingData data) {
		var queryDate = data.date();
		var now = LocalDateTime.now();
		var differenceInMinutes = Duration.between(now, queryDate).toMinutes();

		if (differenceInMinutes < 30) {
			throw new ExceptionValidation("Appointment must be scheduled at least 30 minutes in advance!");
		}
	}
}
