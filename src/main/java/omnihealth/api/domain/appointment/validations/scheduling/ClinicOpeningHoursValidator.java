package omnihealth.api.domain.appointment.validations.scheduling;

import java.time.DayOfWeek;

import org.springframework.stereotype.Component;

import omnihealth.api.domain.ExceptionValidation;
import omnihealth.api.domain.appointment.AppointmentSchedulingData;

@Component
public class ClinicOpeningHoursValidator implements AppointmentScheduleValidator {

	public void validate(AppointmentSchedulingData data) {
		var queryDate = data.date();
		var sunday = queryDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);
		var openingHours = queryDate.getHour() < 7 || queryDate.getHour() > 18;
		if (sunday || openingHours) {
			throw new ExceptionValidation("Appointment outside clinic opening hours!");
		}

	}
}
