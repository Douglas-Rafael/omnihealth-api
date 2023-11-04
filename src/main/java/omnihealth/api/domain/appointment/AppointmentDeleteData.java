package omnihealth.api.domain.appointment;

import jakarta.validation.constraints.NotNull;

public record AppointmentDeleteData(@NotNull Long id,

		@NotNull CancellationReason reason) {

}
