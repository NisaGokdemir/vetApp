package org.codewhiskers.vetapp.service;

import org.codewhiskers.vetapp.common.service.IGenericService;
import org.codewhiskers.vetapp.dto.VaccinationBatches.request.VaccinationBatchesRequestDTO;
import org.codewhiskers.vetapp.dto.VaccinationBatches.response.VaccinationBatchesResponseDTO;

public interface IVaccinationBatchService extends IGenericService<VaccinationBatchesRequestDTO, VaccinationBatchesResponseDTO, Long> {
} 