package omnihealth.api.domain.appointment.validations.cancellation;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import jakarta.validation.ValidationException;
import omnihealth.api.domain.appointment.AppointmentDeleteData;
import omnihealth.api.domain.appointment.AppointmentRepository;

@Component("AdvanceTimeCancellationValidator")
public class AdvanceTimeValidator implements AppointmentCancellationValidator {

	private AppointmentRepository repository;

	@Override
	public void validate(AppointmentDeleteData data) {
		var appointment = repository.getReferenceById(data.id());

		if (!LocalDateTime.now().isBefore(appointment.getDate().minusDays(1))) {
			throw new ValidationException("Appointment cannot be canceled with less than 24 hours left!");
		}

	}

}
