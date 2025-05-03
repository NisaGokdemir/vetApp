package org.codewhiskers.vetapp.controller.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.controller.IRestMedicationBatchController;
import org.codewhiskers.vetapp.dto.MedicationBatch.request.MedicationBatchRequestDTO;
import org.codewhiskers.vetapp.dto.MedicationBatch.response.MedicationBatchResponseDTO;
import org.codewhiskers.vetapp.service.IMedicationBatchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/medication-batches")
@RequiredArgsConstructor
public class RestMedicationBatchController implements IRestMedicationBatchController {

    private final IMedicationBatchService service;

    @PostMapping
    @Override
    public ResponseEntity<MedicationBatchResponseDTO> create(@RequestBody @Valid MedicationBatchRequestDTO requestDTO) {
        MedicationBatchResponseDTO createdBatch = service.create(requestDTO);
        return new ResponseEntity<>(createdBatch, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<MedicationBatchResponseDTO> getById(@PathVariable Long id) {
        MedicationBatchResponseDTO batch = service.getById(id);
        return ResponseEntity.ok(batch);
    }

    @GetMapping
    @Override
    public ResponseEntity<Page<MedicationBatchResponseDTO>> getAll(Pageable pageable) {
        Page<MedicationBatchResponseDTO> batches = service.getAll(pageable);
        return ResponseEntity.ok(batches);
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<MedicationBatchResponseDTO> update(@PathVariable Long id, @RequestBody @Valid MedicationBatchRequestDTO requestDTO) {
        MedicationBatchResponseDTO updatedBatch = service.update(id, requestDTO);
        return ResponseEntity.ok(updatedBatch);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
