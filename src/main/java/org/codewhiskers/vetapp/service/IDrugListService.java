package org.codewhiskers.vetapp.service;

import org.codewhiskers.vetapp.common.service.IGenericService;
import org.codewhiskers.vetapp.dto.DrugList.request.DrugListRequestDTO;
import org.codewhiskers.vetapp.dto.DrugList.response.DrugListResponseDTO;

public interface IDrugListService extends IGenericService<DrugListRequestDTO, DrugListResponseDTO, Long> {
} 