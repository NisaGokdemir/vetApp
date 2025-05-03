package org.codewhiskers.vetapp.dto.VaccineList.response;

import lombok.Getter;
import lombok.Setter;
import org.codewhiskers.vetapp.dto.Species.response.SpeciesResponseDTO;
import org.codewhiskers.vetapp.dto.Vaccination.response.VaccinationResponseDTO;
import org.codewhiskers.vetapp.dto.VaccineType.response.VaccineTypeResponseDTO;

@Getter
@Setter
public class VaccineListResponseDTO {
    private Long id;
    private VaccinationResponseDTO vaccination;
    private String diseaseTarget;
    private SpeciesResponseDTO species;
    private VaccineTypeResponseDTO vaccineType;
    private String notes;
}