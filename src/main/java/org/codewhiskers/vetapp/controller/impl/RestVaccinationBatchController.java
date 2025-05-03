package org.codewhiskers.vetapp.controller.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.controller.IRestVaccinationBatchController;
import org.codewhiskers.vetapp.dto.VaccinationBatches.request.VaccinationBatchesRequestDTO;
import org.codewhiskers.vetapp.dto.VaccinationBatches.response.VaccinationBatchesResponseDTO;
import org.codewhiskers.vetapp.service.IVaccinationBatchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vaccination-batches")
@RequiredArgsConstructor
public class RestVaccinationBatchController implements IRestVaccinationBatchController {

    private final IVaccinationBatchService service;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<VaccinationBatchesResponseDTO> create(@Valid @RequestBody VaccinationBatchesRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(requestDTO));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<VaccinationBatchesResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<VaccinationBatchesResponseDTO>> getAll(Pageable pageable) {
        return ResponseEntity.ok(service.getAll(pageable));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<VaccinationBatchesResponseDTO> update(@PathVariable Long id, @Valid @RequestBody VaccinationBatchesRequestDTO requestDTO) {
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