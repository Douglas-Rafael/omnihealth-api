package omnihealth.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import omnihealth.api.domain.patient.Patient;
import omnihealth.api.domain.patient.PatientDetailData;
import omnihealth.api.domain.patient.PatientListData;
import omnihealth.api.domain.patient.PatientRegistrationData;
import omnihealth.api.domain.patient.PatientRepository;
import omnihealth.api.domain.patient.PatientUpdateData;

@RestController
@RequestMapping("/patients")
@SecurityRequirement(name = "bearer-key")
public class PatientController {

	@Autowired
	private PatientRepository repository;

	@PostMapping
	@Transactional

	public ResponseEntity register(@RequestBody @Valid PatientRegistrationData data, UriComponentsBuilder uriBuilder) {
		var patient = new Patient(data);
		repository.save(patient);

		var uri = uriBuilder.path("/patients/{id}").buildAndExpand(patient.getId()).toUri();

		return ResponseEntity.created(uri).body(new PatientDetailData(patient));

	}

	@GetMapping
	public ResponseEntity<Page<PatientListData>> list(
			@PageableDefault(size = 10, sort = { "name" }) Pageable pagination) {
		var page = repository.findAllByActiveTrue(pagination).map(PatientListData::new);

		return ResponseEntity.ok(page);
	}

	@PutMapping
	@Transactional
	public ResponseEntity update(@RequestBody @Valid PatientUpdateData data) {
		var patient = repository.getReferenceById(data.id());
		patient.dataUpdate(data);

		return ResponseEntity.ok(new PatientDetailData(patient));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity delete(@PathVariable Long id) {
		var patient = repository.getReferenceById(id);
		patient.delete();

		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity detail(@PathVariable Long id) {
		var patient = repository.getReferenceById(id);

		return ResponseEntity.ok(new PatientDetailData(patient));
	}
}
