package org.codewhiskers.vetapp.controller;

import org.codewhiskers.vetapp.dto.MedicationBatch.request.MedicationBatchRequestDTO;
import org.codewhiskers.vetapp.dto.MedicationBatch.response.MedicationBatchResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface IRestMedicationBatchController {

    ResponseEntity<Page<MedicationBatchResponseDTO>> getAllMedicationBatch(Pageable pageable);

    ResponseEntity<MedicationBatchResponseDTO> getMedicationBatch(Long id);

    ResponseEntity<MedicationBatchResponseDTO> createMedicationBatch(MedicationBatchRequestDTO medicationBatchRequestDTO);

    ResponseEntity<MedicationBatchResponseDTO> updateMedicationBatch(Long id, MedicationBatchRequestDTO medicationBatchRequestDTO);

    void deleteMedicationBatch(Long id);
}
