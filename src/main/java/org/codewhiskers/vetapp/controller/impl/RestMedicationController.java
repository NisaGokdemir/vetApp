package org.codewhiskers.vetapp.controller.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.controller.IRestMedicationController;
import org.codewhiskers.vetapp.dto.Medication.request.MedicationRequestDTO;
import org.codewhiskers.vetapp.dto.Medication.response.MedicationResponseDTO;
import org.codewhiskers.vetapp.service.IMedicationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/medications")
@RequiredArgsConstructor
public class RestMedicationController implements IRestMedicationController {

    private final IMedicationService service;

    @PostMapping
    @Override
    public ResponseEntity<MedicationResponseDTO> create(@RequestBody @Valid MedicationRequestDTO requestDTO) {
        MedicationResponseDTO createdMedication = service.create(requestDTO);
        return new ResponseEntity<>(createdMedication, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<MedicationResponseDTO> getById(@PathVariable Long id) {
        MedicationResponseDTO medication = service.getById(id);
        return ResponseEntity.ok(medication);
    }

    @GetMapping
    @Override
    public ResponseEntity<Page<MedicationResponseDTO>> getAll(Pageable pageable) {
        Page<MedicationResponseDTO> medications = service.getAll(pageable);
        return ResponseEntity.ok(medications);
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<MedicationResponseDTO> update(@PathVariable Long id, @RequestBody @Valid MedicationRequestDTO requestDTO) {
        MedicationResponseDTO updatedMedication = service.update(id, requestDTO);
        return ResponseEntity.ok(updatedMedication);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
