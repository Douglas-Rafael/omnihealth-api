package omnihealth.api.domain.appointment.validations.scheduling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.validation.ValidationException;
import omnihealth.api.domain.appointment.AppointmentSchedulingData;
import omnihealth.api.domain.patient.PatientRepository;

@Component
public class PatientActiveValidator implements AppointmentScheduleValidator {

	@Autowired
	private PatientRepository repository;

	public void validate(AppointmentSchedulingData data) {

		var patientIsActive = repository.findActiveById(data.patientId());
		if (!patientIsActive) {
			throw new ValidationException("Consultation cannot be scheduled with a patient inactive!");
		}

	}
}
