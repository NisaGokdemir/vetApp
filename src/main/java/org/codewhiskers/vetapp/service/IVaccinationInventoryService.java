package org.codewhiskers.vetapp.service;

import org.codewhiskers.vetapp.common.service.IGenericService;
import org.codewhiskers.vetapp.dto.VaccinationInventory.request.VaccinationInventoryRequestDTO;
import org.codewhiskers.vetapp.dto.VaccinationInventory.response.VaccinationInventoryResponseDTO;

public interface IVaccinationInventoryService extends IGenericService<VaccinationInventoryRequestDTO, VaccinationInventoryResponseDTO, Long> {
} 