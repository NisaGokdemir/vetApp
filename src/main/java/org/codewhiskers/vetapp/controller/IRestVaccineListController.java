package org.codewhiskers.vetapp.controller;

import org.codewhiskers.vetapp.common.service.IGenericRestController;
import org.codewhiskers.vetapp.dto.VaccineList.request.VaccineListRequestDTO;
import org.codewhiskers.vetapp.dto.VaccineList.response.VaccineListResponseDTO;

public interface IRestVaccineListController extends IGenericRestController<VaccineListRequestDTO, VaccineListResponseDTO, Long> {
} 