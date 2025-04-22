package org.codewhiskers.vetapp.controller.impl;

import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.controller.IRestMedicationController;
import org.codewhiskers.vetapp.dto.Medication.request.MedicationRequestDTO;
import org.codewhiskers.vetapp.dto.Medication.response.MedicationResponseDTO;
import org.codewhiskers.vetapp.service.IMedicationService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/medication")
public class RestMedicationController implements IRestMedicationController {

    private final IMedicationService medicationService;

    @GetMapping("/all")
    @Override
    public ResponseEntity<Page<MedicationResponseDTO>> getAllMedications(@RequestParam(defaultValue = "0") int page,
                                                                         @RequestParam(defaultValue = "10") int size) {
        Page<MedicationResponseDTO> medications = medicationService.getAllMedications(page, size);
        if(medications.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(medications);
    }

    @GetMapping("/find/{id}")
    @Override
    public ResponseEntity<MedicationResponseDTO> getMedicationById(@PathVariable(name = "id") Long id) {
        MedicationResponseDTO medicationResponseDTO = medicationService.getMedicationById(id);
        if(medicationResponseDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(medicationResponseDTO);
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public void DeleteMedicationById(@PathVariable(name = "id") Long id) {
        medicationService.DeleteMedicationById(id);
        ResponseEntity.ok().build();
    }

    @PostMapping("/create")
    @Override
    public ResponseEntity<MedicationResponseDTO> createMedication(@RequestBody MedicationRequestDTO medication) {
        MedicationResponseDTO medicationResponseDTO = medicationService.createMedication(medication);
        if(medicationResponseDTO == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(medicationResponseDTO);
    }

    @PutMapping("/update/{id}")
    @Override
    public ResponseEntity<MedicationResponseDTO> updateMedication(@PathVariable(name = "id") Long id, @RequestBody MedicationRequestDTO medication) {
        MedicationResponseDTO medicationResponseDTO = medicationService.updateMedication(id, medication);
        if(medicationResponseDTO == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(medicationResponseDTO);
    }
}
