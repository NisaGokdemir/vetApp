package org.codewhiskers.vetapp.dto.DrugList.response;

import lombok.Getter;
import lombok.Setter;
import org.codewhiskers.vetapp.dto.Drug.response.DrugResponseDTO;
import org.codewhiskers.vetapp.dto.Species.response.SpeciesResponseDTO; // Varsayalım ki SpeciesResponseDTO'nuz var
import org.codewhiskers.vetapp.dto.UsageArea.response.UsageAreaResponseDTO;

@Getter
@Setter
public class DrugListResponseDTO {
    private Long id;
    private DrugResponseDTO drug;
    private String activeSubstance;
    private UsageAreaResponseDTO usageArea;
    private SpeciesResponseDTO species;
    private String administrationRoute;
}