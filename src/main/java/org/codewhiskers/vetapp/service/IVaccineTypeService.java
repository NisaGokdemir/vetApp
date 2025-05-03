package org.codewhiskers.vetapp.service;

import org.codewhiskers.vetapp.common.service.IGenericService;
import org.codewhiskers.vetapp.dto.VaccineType.request.VaccineTypeRequestDTO;
import org.codewhiskers.vetapp.dto.VaccineType.response.VaccineTypeResponseDTO;

public interface IVaccineTypeService extends IGenericService<VaccineTypeRequestDTO, VaccineTypeResponseDTO, Long> {
} 