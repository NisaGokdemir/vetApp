package org.codewhiskers.vetapp.controller;

import jakarta.validation.Valid;
import org.codewhiskers.vetapp.common.service.IGenericRestController;
import org.codewhiskers.vetapp.dto.Species.request.SpeciesRequestDTO;
import org.codewhiskers.vetapp.dto.Species.response.SpeciesResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

/*
public interface IRestSpeciesController {

    ResponseEntity<SpeciesResponseDTO> createSpecies(@Valid SpeciesRequestDTO speciesRequestDTO);

    ResponseEntity<SpeciesResponseDTO> getSpeciesById(Long id);

    ResponseEntity<Page<SpeciesResponseDTO>> getAllSpecies(Pageable pageable);

    ResponseEntity<SpeciesResponseDTO> updateSpecies(Long id, @Valid SpeciesRequestDTO speciesRequestDTO);

    ResponseEntity<Void> deleteSpecies(Long id);
}
*/

public interface IRestSpeciesController extends IGenericRestController<SpeciesRequestDTO, SpeciesResponseDTO, Long> {
}
