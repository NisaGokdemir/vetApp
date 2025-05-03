package org.codewhiskers.vetapp.service;

import org.codewhiskers.vetapp.common.service.IGenericService;
import org.codewhiskers.vetapp.dto.UsageArea.request.UsageAreaRequestDTO;
import org.codewhiskers.vetapp.dto.UsageArea.response.UsageAreaResponseDTO;

public interface IUsageAreaService extends IGenericService<UsageAreaRequestDTO, UsageAreaResponseDTO, Long> {
} 