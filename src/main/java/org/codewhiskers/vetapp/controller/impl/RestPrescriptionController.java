package org.codewhiskers.vetapp.controller.impl;

import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.controller.IRestPrescriptionController;
import org.codewhiskers.vetapp.dto.Prescription.request.PrescriptionRequestDTO;
import org.codewhiskers.vetapp.dto.Prescription.response.PrescriptionResponseDTO;
import org.codewhiskers.vetapp.service.IPrescriptionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/prescriptions")
public class RestPrescriptionController implements IRestPrescriptionController {

    private final IPrescriptionService prescriptionService;

    @GetMapping("/all")
    @Override
    public ResponseEntity<Page<PrescriptionResponseDTO>> getAllPrescriptions(Pageable pageable) {
        Page<PrescriptionResponseDTO> prescriptions = prescriptionService.getAllPrescriptions(pageable);
        if (!prescriptions.hasContent()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(prescriptions);
    }

    @GetMapping("/find/{id}")
    @Override
    public ResponseEntity<PrescriptionResponseDTO> getPrescriptionById(@PathVariable Long id) {
        PrescriptionResponseDTO prescription = prescriptionService.getPrescriptionById(id);
        if(prescription == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(prescription);
    }

    @PostMapping("/create")
    @Override
    public ResponseEntity<PrescriptionResponseDTO> createPrescription(@RequestBody PrescriptionRequestDTO prescriptionRequestDTO) {
        PrescriptionResponseDTO prescription = prescriptionService.createPrescription(prescriptionRequestDTO);
        if(prescription == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(prescription);
    }

    @PutMapping("/update/{id}")
    @Override
    public ResponseEntity<PrescriptionResponseDTO> updatePrescription(@PathVariable Long id, @RequestBody PrescriptionRequestDTO prescriptionRequestDTO) {
        PrescriptionResponseDTO prescription = prescriptionService.updatePrescription(id, prescriptionRequestDTO);
        if(prescription == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(prescription);
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public void deletePrescription(@PathVariable Long id) {
        prescriptionService.deletePrescription(id);
        ResponseEntity.ok().build();
    }

    @GetMapping("/diagnosis/{diagnosisId}")
    @Override
    public ResponseEntity<Page<PrescriptionResponseDTO>> getPrescriptionsByDiagnosisId(@PathVariable Long diagnosisId, Pageable pageable) {
        Page<PrescriptionResponseDTO> prescriptions = prescriptionService.getPrescriptionsByDiagnosisId(diagnosisId, pageable);
        if (!prescriptions.hasContent()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(prescriptions);
    }
}
