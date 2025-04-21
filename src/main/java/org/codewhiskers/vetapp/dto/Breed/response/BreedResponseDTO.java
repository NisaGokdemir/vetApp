package org.codewhiskers.vetapp.dto.Breed.response;

import lombok.Data;
import org.codewhiskers.vetapp.dto.Species.response.SpeciesResponseDTO;
import org.codewhiskers.vetapp.entity.Species;

@Data
public class BreedResponseDTO {
    private Long id;
    private String name;
    private SpeciesResponseDTO species;
}