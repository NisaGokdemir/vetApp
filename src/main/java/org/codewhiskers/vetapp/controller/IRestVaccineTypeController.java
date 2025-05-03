package org.codewhiskers.vetapp.controller;

import org.codewhiskers.vetapp.common.service.IGenericRestController;
import org.codewhiskers.vetapp.dto.VaccineType.request.VaccineTypeRequestDTO;
import org.codewhiskers.vetapp.dto.VaccineType.response.VaccineTypeResponseDTO;

public interface IRestVaccineTypeController extends IGenericRestController<VaccineTypeRequestDTO, VaccineTypeResponseDTO, Long> {
} 