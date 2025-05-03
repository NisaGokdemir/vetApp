package org.codewhiskers.vetapp.dto.DrugProtocol.response;

import lombok.Getter;
import lombok.Setter;
import org.codewhiskers.vetapp.dto.Drug.response.DrugResponseDTO;
import org.codewhiskers.vetapp.dto.Species.response.SpeciesResponseDTO;

@Getter
@Setter
public class DrugProtocolResponseDTO {
    private Long id;
    private DrugResponseDTO drug;
    private SpeciesResponseDTO species;
    private Integer minAge;
    private Double maxDoseMgKg;
    private String frequency;
    private String notes;
}