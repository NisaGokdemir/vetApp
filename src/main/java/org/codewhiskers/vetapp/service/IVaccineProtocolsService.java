package org.codewhiskers.vetapp.service;

import org.codewhiskers.vetapp.common.service.IGenericService;
import org.codewhiskers.vetapp.dto.VaccineProtocols.request.VaccineProtocolsRequestDTO;
import org.codewhiskers.vetapp.dto.VaccineProtocols.response.VaccineProtocolsResponseDTO;

public interface IVaccineProtocolsService extends IGenericService<VaccineProtocolsRequestDTO, VaccineProtocolsResponseDTO, Long> {
} 