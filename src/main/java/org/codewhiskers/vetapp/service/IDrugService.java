package org.codewhiskers.vetapp.service;

import org.codewhiskers.vetapp.common.service.IGenericService;
import org.codewhiskers.vetapp.dto.Drug.request.DrugRequestDTO;
import org.codewhiskers.vetapp.dto.Drug.response.DrugResponseDTO;

public interface IDrugService extends IGenericService<DrugRequestDTO, DrugResponseDTO, Long> {
} 