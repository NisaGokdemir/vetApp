package org.codewhiskers.vetapp.controller;

import jakarta.validation.Valid;
import org.codewhiskers.vetapp.dto.Species.request.SpeciesRequestDTO;
import org.codewhiskers.vetapp.dto.Species.response.SpeciesResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IRestSpeciesController {

    SpeciesResponseDTO createSpecies(@Valid SpeciesRequestDTO speciesRequestDTO);

    SpeciesResponseDTO getSpeciesById(Long id);

    Page<SpeciesResponseDTO> getAllSpecies(Pageable pageable);

    SpeciesResponseDTO updateSpecies(Long id,@Valid SpeciesRequestDTO speciesRequestDTO);

    void deleteSpecies(Long id);
}
