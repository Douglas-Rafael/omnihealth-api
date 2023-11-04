package omnihealth.api.domain.patient;

import omnihealth.api.domain.address.Address;

public record PatientDetailData(Long id, String name, String email, String telephone, Address adress) {

	public PatientDetailData(Patient patient) {
		this(patient.getId(), patient.getName(), patient.getEmail(), patient.getTelephone(), patient.getAddress());
	}

}
