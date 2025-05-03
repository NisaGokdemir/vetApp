package org.codewhiskers.vetapp.controller;

import org.codewhiskers.vetapp.common.service.IGenericRestController;
import org.codewhiskers.vetapp.dto.DrugList.request.DrugListRequestDTO;
import org.codewhiskers.vetapp.dto.DrugList.response.DrugListResponseDTO;

public interface IRestDrugListController extends IGenericRestController<DrugListRequestDTO, DrugListResponseDTO, Long> {
} 