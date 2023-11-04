package omnihealth.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import omnihealth.api.domain.appointment.AppointmentDeleteData;
import omnihealth.api.domain.appointment.AppointmentSchedulingData;
import omnihealth.api.domain.appointment.AppointmentService;

@RestController
@RequestMapping("/appointments")
@SecurityRequirement(name = "bearer-key")
public class AppointmentController {

	@Autowired
	private AppointmentService service;

	@PostMapping
	@Transactional
	public ResponseEntity toSchedule(@RequestBody @Valid AppointmentSchedulingData data,
			UriComponentsBuilder uriBuilder) {
		var appointmentDetailData = service.toSchedule(data);

		return ResponseEntity.ok(appointmentDetailData);
	}

	@DeleteMapping
	@Transactional
	public ResponseEntity deleteAppointment(@RequestBody @Valid AppointmentDeleteData data) {
		service.deleteAppointment(data);

		return ResponseEntity.noContent().build();
	}
}
