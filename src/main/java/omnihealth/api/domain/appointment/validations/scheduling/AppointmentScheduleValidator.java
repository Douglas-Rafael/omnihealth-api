package omnihealth.api.domain.appointment.validations.scheduling;

import omnihealth.api.domain.appointment.AppointmentSchedulingData;

public interface AppointmentScheduleValidator {

	void validate(AppointmentSchedulingData data);
}
