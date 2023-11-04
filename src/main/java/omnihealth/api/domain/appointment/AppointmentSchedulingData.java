package omnihealth.api.domain.appointment;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import omnihealth.api.domain.doctor.Specialty;

public record AppointmentSchedulingData(Long doctorId,

		@NotNull Long patientId,

		@NotNull @Future LocalDateTime date,

		Specialty specialty) {

}
