package org.codewhiskers.vetapp.controller;

import org.codewhiskers.vetapp.common.service.IGenericRestController;
import org.codewhiskers.vetapp.dto.DrugProtocol.request.DrugProtocolRequestDTO;
import org.codewhiskers.vetapp.dto.DrugProtocol.response.DrugProtocolResponseDTO;

public interface IRestDrugProtocolController extends IGenericRestController<DrugProtocolRequestDTO, DrugProtocolResponseDTO, Long> {
} 