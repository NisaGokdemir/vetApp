package org.codewhiskers.vetapp.service;

import org.codewhiskers.vetapp.common.service.IGenericService;
import org.codewhiskers.vetapp.dto.Medication.request.MedicationRequestDTO;
import org.codewhiskers.vetapp.dto.Medication.response.MedicationResponseDTO;

public interface IMedicationService extends IGenericService<MedicationRequestDTO, MedicationResponseDTO, Long> {
}
