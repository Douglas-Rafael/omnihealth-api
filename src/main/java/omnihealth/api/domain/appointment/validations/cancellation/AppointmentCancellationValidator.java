package omnihealth.api.domain.appointment.validations.cancellation;

import omnihealth.api.domain.appointment.AppointmentDeleteData;

public interface AppointmentCancellationValidator {

	void validate(AppointmentDeleteData data);

}
