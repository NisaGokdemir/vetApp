package org.codewhiskers.vetapp.service;

import org.codewhiskers.vetapp.common.service.IGenericService;
import org.codewhiskers.vetapp.dto.DrugProtocol.request.DrugProtocolRequestDTO;
import org.codewhiskers.vetapp.dto.DrugProtocol.response.DrugProtocolResponseDTO;

public interface IDrugProtocolService extends IGenericService<DrugProtocolRequestDTO, DrugProtocolResponseDTO, Long> {
}
