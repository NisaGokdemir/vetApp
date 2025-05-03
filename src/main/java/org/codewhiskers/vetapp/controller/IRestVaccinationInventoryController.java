package org.codewhiskers.vetapp.controller;

import org.codewhiskers.vetapp.common.service.IGenericRestController;
import org.codewhiskers.vetapp.dto.VaccinationInventory.request.VaccinationInventoryRequestDTO;
import org.codewhiskers.vetapp.dto.VaccinationInventory.response.VaccinationInventoryResponseDTO;

public interface IRestVaccinationInventoryController extends IGenericRestController<VaccinationInventoryRequestDTO, VaccinationInventoryResponseDTO, Long> {
} 