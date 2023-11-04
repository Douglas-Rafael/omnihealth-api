package omnihealth.api.domain.appointment.validations.scheduling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import omnihealth.api.domain.ExceptionValidation;
import omnihealth.api.domain.appointment.AppointmentSchedulingData;
import omnihealth.api.domain.doctor.DoctorRepository;

@Component
public class DoctorActiveValidator implements AppointmentScheduleValidator {

	@Autowired
	private DoctorRepository repository;

	public void validate(AppointmentSchedulingData data) {

		if (data.doctorId() == null) {
			return;
		}

		var doctorIsActive = repository.findActiveById(data.doctorId());
		if (!doctorIsActive) {
			throw new ExceptionValidation("Consultation cannot be scheduled with a doctor inactive!");
		}

	}

}
