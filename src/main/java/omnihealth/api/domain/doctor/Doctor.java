package omnihealth.api.domain.doctor;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import omnihealth.api.domain.address.Address;

@Table(name = "doctors")
@Entity(name = "Doctor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {

	public Doctor(DoctorRegistrationData data) {
		this.name = data.name();
		this.email = data.email();
		this.crm = data.crm();
		this.specialty = data.specialty();
		this.address = new Address(data.address());
		this.telephone = data.telephone();
		this.active = true;

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String telephone;
	private String crm;
	private Boolean active;

	@Enumerated(EnumType.STRING)
	private Specialty specialty;

	@Embedded
	private Address address;

	public void dataUpdate(@Valid DoctorUpdateData data) {
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
