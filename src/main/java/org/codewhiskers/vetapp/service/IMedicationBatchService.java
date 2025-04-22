package org.codewhiskers.vetapp.service;

import org.codewhiskers.vetapp.dto.MedicationBatch.request.MedicationBatchRequestDTO;
import org.codewhiskers.vetapp.dto.MedicationBatch.response.MedicationBatchResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IMedicationBatchService {

    Page<MedicationBatchResponseDTO> getAllMedicationBatch(Pageable pageable);

    MedicationBatchResponseDTO getMedicationBatch(Long id);

    MedicationBatchResponseDTO createMedicationBatch(MedicationBatchRequestDTO medicationBatchRequestDTO);

    MedicationBatchResponseDTO updateMedicationBatch(Long id, MedicationBatchRequestDTO medicationBatchRequestDTO);

    void deleteMedicationBatch(Long id);
}
