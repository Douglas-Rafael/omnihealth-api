package omnihealth.api.domain.appointment.validations.cancellation;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.ValidationException;
import omnihealth.api.domain.appointment.AppointmentDeleteData;
import omnihealth.api.domain.appointment.AppointmentRepository;

public class AppointmentActiveValidator implements AppointmentCancellationValidator {

	@Autowired
	private AppointmentRepository repository;

	@Override
	public void validate(AppointmentDeleteData data) {

		var appointment = repository.findActiveById(data.id());
		if (!appointment) {
			throw new ValidationException("The appointment has already been canceled");
		}

	}

}
