package omnihealth.api.domain.patient;

import jakarta.validation.constraints.NotNull;
import omnihealth.api.domain.address.AddressData;

public record PatientUpdateData(@NotNull Long id, String name, String telephone, AddressData address) {

}
