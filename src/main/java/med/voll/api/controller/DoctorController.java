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
import med.voll.api.doctor.Doctor;
import med.voll.api.doctor.DoctorListData;
import med.voll.api.doctor.DoctorRegistrationData;
import med.voll.api.doctor.DoctorRepository;
import med.voll.api.doctor.DoctorUpdateData;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

	@Autowired
	private DoctorRepository repository;

	@PostMapping
	@Transactional
	public void register(@RequestBody @Valid DoctorRegistrationData data) {
		repository.save(new Doctor(data));
	}

	@GetMapping
	public Page<DoctorListData> listar(@PageableDefault(size = 10, sort = { "name" }) Pageable pagination) {
		return repository.findAllByActiveTrue(pagination).map(DoctorListData::new);
	}

	@PutMapping
	@Transactional
	public void update(@RequestBody @Valid DoctorUpdateData data) {
		repository.getReferenceById(data.id()).dataUpdate(data);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public void delete(@PathVariable Long id) {
		repository.getReferenceById(id).delete();
	}
}
