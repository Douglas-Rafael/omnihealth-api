package omnihealth.api.domain.doctor;

import omnihealth.api.domain.address.Address;

public record DoctorDetailData(Long id, String name, String email, String crm, String telephone, Specialty specialty,
		Address adress) {

	public DoctorDetailData(Doctor doctor) {
		this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getTelephone(),
				doctor.getSpecialty(), doctor.getAddress());
	}

}
