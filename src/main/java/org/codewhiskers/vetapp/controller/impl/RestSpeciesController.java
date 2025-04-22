package org.codewhiskers.vetapp.controller.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.controller.IRestSpeciesController;
import org.codewhiskers.vetapp.dto.Species.request.SpeciesRequestDTO;
import org.codewhiskers.vetapp.dto.Species.response.SpeciesResponseDTO;
import org.codewhiskers.vetapp.service.ISpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/species")
public class RestSpeciesController implements IRestSpeciesController {

    private final ISpeciesService speciesService;

    @Override
    @PostMapping
    public ResponseEntity<SpeciesResponseDTO> createSpecies(@RequestBody @Valid SpeciesRequestDTO speciesRequestDTO) {
        SpeciesResponseDTO response = speciesService.createSpecies(speciesRequestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<SpeciesResponseDTO> getSpeciesById(@PathVariable Long id) {
        SpeciesResponseDTO response = speciesService.getSpeciesById(id);
        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<SpeciesResponseDTO>> getAllSpecies(Pageable pageable) {
        Page<SpeciesResponseDTO> response = speciesService.getAllSpecies(pageable);
        return ResponseEntity.ok(response);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<SpeciesResponseDTO> updateSpecies(@PathVariable Long id,
                                                            @RequestBody @Valid SpeciesRequestDTO speciesRequestDTO) {
        SpeciesResponseDTO response = speciesService.updateSpecies(id, speciesRequestDTO);
        return ResponseEntity.ok(response);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpecies(@PathVariable Long id) {
        speciesService.deleteSpecies(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}
