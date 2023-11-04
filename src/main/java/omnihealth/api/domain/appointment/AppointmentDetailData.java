package omnihealth.api.domain.appointment;

import java.time.LocalDateTime;

public record AppointmentDetailData(Long id, Long doctorId, Long patientId, LocalDateTime date) {

	public AppointmentDetailData(Appointment appointment) {
		this(appointment.getId(), appointment.getDoctor().getId(), appointment.getPatient().getId(),
				appointment.getDate());
	}

}
