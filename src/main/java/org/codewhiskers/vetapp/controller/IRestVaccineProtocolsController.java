package org.codewhiskers.vetapp.controller;

import org.codewhiskers.vetapp.common.service.IGenericRestController;
import org.codewhiskers.vetapp.dto.VaccineProtocols.request.VaccineProtocolsRequestDTO;
import org.codewhiskers.vetapp.dto.VaccineProtocols.response.VaccineProtocolsResponseDTO;

public interface IRestVaccineProtocolsController extends IGenericRestController<VaccineProtocolsRequestDTO, VaccineProtocolsResponseDTO, Long> {
} 