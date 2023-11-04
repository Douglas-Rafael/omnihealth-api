package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.patient.Patient;
import med.voll.api.patient.PatientListData;
import med.voll.api.patient.PatientRegistrationData;
import med.voll.api.patient.PatientRepository;
import med.voll.api.patient.PatientUpdateData;

@RestController
@RequestMapping("/patients")
public class PatientController {

	@Autowired
	private PatientRepository repository;

	@PostMapping
	@Transactional
	public void register(@RequestBody @Valid PatientRegistrationData data) {
		repository.save(new Patient(data));

	}

	@GetMapping
	public Page<PatientListData> list(@PageableDefault(size = 10, sort = { "name" }) Pageable pagination) {
		return repository.findAllByActiveTrue(pagination).map(PatientListData::new);
	}

	@PutMapping
	@Transactional
	public void update(@RequestBody @Valid PatientUpdateData data) {
		var patient = repository.getReferenceById(data.id());
		patient.dataUpdate(data);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public void delete(@PathVariable Long id) {

		var patient = repository.getReferenceById(id);
		patient.delete();
	}
}
