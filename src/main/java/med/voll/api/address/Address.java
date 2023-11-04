package med.voll.api.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

	public Address(AddressData data) {
		this.publicArea = data.publicArea();
		this.neighborhood = data.neighborhood();
		this.cep = data.cep();
		this.number = data.number();
		this.complement = data.complement();
		this.city = data.city();
		this.uf = data.uf();
	}

	private String publicArea;
	private String neighborhood;
	private String cep;
	private String number;
	private String complement;
	private String city;
	private String uf;

	public void dataUpdate(AddressData data) {
		if (data.publicArea() != null) {
			this.publicArea = data.publicArea();
		}
		if (data.neighborhood() != null) {
			this.neighborhood = data.neighborhood();
		}
		if (data.cep() != null) {
			this.cep = data.cep();
		}
		if (data.number() != null) {
			this.number = data.number();
		}
		if (data.complement() != null) {
			this.complement = data.complement();
		}
		if (data.city() != null) {
			this.city = data.city();
		}
		if (data.uf() != null) {
			this.uf = data.uf();
		}
	}
}
