package org.codewhiskers.vetapp.dto.bloodType.response;

import lombok.Data;
import org.codewhiskers.vetapp.dto.Species.response.SpeciesResponseDTO;

@Data
public class BloodTypeResponseDTO {
    private Long id;
    private String type;
    private SpeciesResponseDTO species;
}