package org.codewhiskers.vetapp.controller.impl;

import jakarta.validation.Valid;
import org.codewhiskers.vetapp.controller.IRestSpeciesController;
import org.codewhiskers.vetapp.dto.Species.request.SpeciesRequestDTO;
import org.codewhiskers.vetapp.dto.Species.response.SpeciesResponseDTO;
import org.codewhiskers.vetapp.service.ISpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/species")
@Validated
public class RestSpeciesController implements IRestSpeciesController {

    private final ISpeciesService speciesService;

    @Autowired
    public RestSpeciesController(ISpeciesService speciesService) {
        this.speciesService = speciesService;
    }

    @PostMapping
    public SpeciesResponseDTO createSpecies(@RequestBody @Valid SpeciesRequestDTO speciesRequestDTO) {
        return speciesService.createSpecies(speciesRequestDTO);
    }

    @GetMapping("/{id}")
    public SpeciesResponseDTO getSpeciesById(@PathVariable Long id) {
        return speciesService.getSpeciesById(id);
    }

    //  /api/species?page=0&size=10
    @GetMapping
    public Page<SpeciesResponseDTO> getAllSpecies(Pageable pageable) {
        return speciesService.getAllSpecies(pageable);
    }

    @PutMapping("/{id}")
    public SpeciesResponseDTO updateSpecies(@PathVariable Long id, @RequestBody @Valid SpeciesRequestDTO speciesRequestDTO) {
        return speciesService.updateSpecies(id, speciesRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteSpecies(@PathVariable Long id) {
        speciesService.deleteSpecies(id);
    }
}
