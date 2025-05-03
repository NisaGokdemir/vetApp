package org.codewhiskers.vetapp.service;

import org.codewhiskers.vetapp.common.service.IGenericService;
import org.codewhiskers.vetapp.dto.MedicationBatch.request.MedicationBatchRequestDTO;
import org.codewhiskers.vetapp.dto.MedicationBatch.response.MedicationBatchResponseDTO;

public interface IMedicationBatchService extends IGenericService<MedicationBatchRequestDTO, MedicationBatchResponseDTO, Long> {
}
