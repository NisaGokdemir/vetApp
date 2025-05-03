package org.codewhiskers.vetapp.controller;

import org.codewhiskers.vetapp.common.service.IGenericRestController;
import org.codewhiskers.vetapp.dto.VaccinationBatches.request.VaccinationBatchesRequestDTO;
import org.codewhiskers.vetapp.dto.VaccinationBatches.response.VaccinationBatchesResponseDTO;

public interface IRestVaccinationBatchController extends IGenericRestController<VaccinationBatchesRequestDTO, VaccinationBatchesResponseDTO, Long> {
} 