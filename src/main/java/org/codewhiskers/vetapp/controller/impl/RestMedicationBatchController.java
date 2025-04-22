package org.codewhiskers.vetapp.controller.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.controller.IRestMedicationBatchController;
import org.codewhiskers.vetapp.dto.MedicationBatch.request.MedicationBatchRequestDTO;
import org.codewhiskers.vetapp.dto.MedicationBatch.response.MedicationBatchResponseDTO;
import org.codewhiskers.vetapp.service.IMedicationBatchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/medication-batch")
public class RestMedicationBatchController implements IRestMedicationBatchController {
     private final IMedicationBatchService medicationBatchService;

     @GetMapping("/all")
     @Override
     public ResponseEntity<Page<MedicationBatchResponseDTO>> getAllMedicationBatch(Pageable pageable) {
         Page<MedicationBatchResponseDTO> medicationBatches = medicationBatchService.getAllMedicationBatch(pageable);
            if (medicationBatches.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
         return ResponseEntity.ok(medicationBatches);
     }

     @GetMapping("/find/{id}")
     @Override
     public ResponseEntity<MedicationBatchResponseDTO> getMedicationBatch(@PathVariable Long id) {
         MedicationBatchResponseDTO medicationBatch = medicationBatchService.getMedicationBatch(id);
            if (medicationBatch == null) {
                return ResponseEntity.notFound().build();
            }
         return ResponseEntity.ok(medicationBatch);
     }

     @PostMapping("/create")
     @Override
     public ResponseEntity<MedicationBatchResponseDTO> createMedicationBatch(@RequestBody @Valid MedicationBatchRequestDTO medicationBatchRequestDTO) {
         MedicationBatchResponseDTO createdMedicationBatch = medicationBatchService.createMedicationBatch(medicationBatchRequestDTO);
            if (createdMedicationBatch == null) {
                return ResponseEntity.badRequest().build();
            }
         return ResponseEntity.ok(createdMedicationBatch);
     }

     @PutMapping("/update/{id}")
     @Override
     public ResponseEntity<MedicationBatchResponseDTO> updateMedicationBatch(@PathVariable Long id, @RequestBody @Valid MedicationBatchRequestDTO medicationBatchRequestDTO) {
         MedicationBatchResponseDTO updatedMedicationBatch = medicationBatchService.updateMedicationBatch(id, medicationBatchRequestDTO);
            if (updatedMedicationBatch == null) {
                return ResponseEntity.notFound().build();
            }
         return ResponseEntity.ok(updatedMedicationBatch);
     }

     @DeleteMapping("/delete/{id}")
     @Override
     public void deleteMedicationBatch(@PathVariable Long id) {
         medicationBatchService.deleteMedicationBatch(id);
         ResponseEntity.ok().build();
     }

}
