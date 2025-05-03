package org.codewhiskers.vetapp.controller;

import org.codewhiskers.vetapp.common.service.IGenericRestController;
import org.codewhiskers.vetapp.dto.Drug.request.DrugRequestDTO;
import org.codewhiskers.vetapp.dto.Drug.response.DrugResponseDTO;

public interface IRestDrugController extends IGenericRestController<DrugRequestDTO, DrugResponseDTO, Long> {
} 