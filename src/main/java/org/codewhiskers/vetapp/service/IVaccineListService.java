package org.codewhiskers.vetapp.service;

import org.codewhiskers.vetapp.common.service.IGenericService;
import org.codewhiskers.vetapp.dto.VaccineList.request.VaccineListRequestDTO;
import org.codewhiskers.vetapp.dto.VaccineList.response.VaccineListResponseDTO;

public interface IVaccineListService extends IGenericService<VaccineListRequestDTO, VaccineListResponseDTO, Long> {
} 