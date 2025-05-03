package org.codewhiskers.vetapp.controller.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.controller.IRestVaccinationInventoryController;
import org.codewhiskers.vetapp.dto.VaccinationInventory.request.VaccinationInventoryRequestDTO;
import org.codewhiskers.vetapp.dto.VaccinationInventory.response.VaccinationInventoryResponseDTO;
import org.codewhiskers.vetapp.service.IVaccinationInventoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vaccination-inventory")
@RequiredArgsConstructor
public class RestVaccinationInventoryController implements IRestVaccinationInventoryController {

    private final IVaccinationInventoryService service;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<VaccinationInventoryResponseDTO> create(@Valid @RequestBody VaccinationInventoryRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(requestDTO));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<VaccinationInventoryResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<VaccinationInventoryResponseDTO>> getAll(Pageable pageable) {
        return ResponseEntity.ok(service.getAll(pageable));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<VaccinationInventoryResponseDTO> update(@PathVariable Long id, @Valid @RequestBody VaccinationInventoryRequestDTO requestDTO) {
        return ResponseEntity.ok(service.update(id, requestDTO));
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
} 