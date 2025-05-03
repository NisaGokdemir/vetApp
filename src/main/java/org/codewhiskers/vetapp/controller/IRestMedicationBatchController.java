package org.codewhiskers.vetapp.controller;

import org.codewhiskers.vetapp.common.service.IGenericRestController;
import org.codewhiskers.vetapp.dto.MedicationBatch.request.MedicationBatchRequestDTO;
import org.codewhiskers.vetapp.dto.MedicationBatch.response.MedicationBatchResponseDTO;

public interface IRestMedicationBatchController extends IGenericRestController<MedicationBatchRequestDTO, MedicationBatchResponseDTO, Long> {
}
