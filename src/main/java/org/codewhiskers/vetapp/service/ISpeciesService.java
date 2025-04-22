package org.codewhiskers.vetapp.service;

import org.codewhiskers.vetapp.dto.Species.request.SpeciesRequestDTO;
import org.codewhiskers.vetapp.dto.Species.response.SpeciesResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ISpeciesService {

    SpeciesResponseDTO createSpecies(SpeciesRequestDTO speciesRequestDTO);

    SpeciesResponseDTO getSpeciesById(Long id);

    Page<SpeciesResponseDTO> getAllSpecies(Pageable pageable);

    SpeciesResponseDTO updateSpecies(Long id, SpeciesRequestDTO speciesRequestDTO);

    void deleteSpecies(Long id);
}