package org.codewhiskers.vetapp.dto.VaccinationInventory.response;

import lombok.Getter;
import lombok.Setter;
import org.codewhiskers.vetapp.dto.Clinic.response.ClinicResponseDTO;
import org.codewhiskers.vetapp.dto.Vaccination.response.VaccinationResponseDTO;

@Getter
@Setter
public class VaccinationInventoryResponseDTO {
    private Long id;
    private VaccinationResponseDTO vaccination;
    private ClinicResponseDTO clinic;
    private Integer stockWarningLevel;
}