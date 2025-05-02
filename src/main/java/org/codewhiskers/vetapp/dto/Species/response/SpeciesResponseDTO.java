package org.codewhiskers.vetapp.dto.Species.response;

import lombok.Data;
import org.codewhiskers.vetapp.dto.Family.response.FamilyResponseDTO;

@Data
public class SpeciesResponseDTO {
    private Long id;
    private String name;

    private FamilyResponseDTO family;
}