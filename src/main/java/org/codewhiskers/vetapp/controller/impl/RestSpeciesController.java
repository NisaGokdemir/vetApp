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
    public ResponseEntity<SpeciesResponseDTO> create(@RequestBody @Valid SpeciesRequestDTO speciesRequestDTO) {
        SpeciesResponseDTO response = speciesService.create(speciesRequestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<SpeciesResponseDTO> getById(@PathVariable Long id) {
        SpeciesResponseDTO response = speciesService.getById(id);
        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<SpeciesResponseDTO>> getAll(Pageable pageable) {
        Page<SpeciesResponseDTO> response = speciesService.getAll(pageable);
        return ResponseEntity.ok(response);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<SpeciesResponseDTO> update(@PathVariable Long id,
                                                            @RequestBody @Valid SpeciesRequestDTO speciesRequestDTO) {
        SpeciesResponseDTO response = speciesService.update(id, speciesRequestDTO);
        return ResponseEntity.ok(response);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        speciesService.delete(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}
