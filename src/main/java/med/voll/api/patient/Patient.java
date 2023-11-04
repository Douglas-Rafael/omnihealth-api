package med.voll.api.patient;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.address.Address;
import med.voll.api.doctor.DoctorUpdateData;

@Table(name = "patients")
@Entity(name = "Patient")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {

	public Patient(PatientRegistrationData data) {
		this.name = data.name();
		this.email = data.email();
		this.address = new Address(data.address());
		this.telephone = data.telephone();
		this.cpf = data.cpf();
		this.active = true;

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String telephone;
	private String cpf;
	private Boolean active;

	@Embedded
	private Address address;

	public void dataUpdate(@Valid PatientUpdateData data) {
		if (data.name() != null) {
			this.name = data.name();
		}
		if (data.telephone() != null) {
			this.telephone = data.telephone();
		}
		if (data.address() != null) {
			this.address.dataUpdate(data.address());
		}

	}

	public void delete() {
		this.active = false;
	}
}
