package org.codewhiskers.vetapp.controller;

import org.codewhiskers.vetapp.common.service.IGenericRestController;
import org.codewhiskers.vetapp.dto.UsageArea.request.UsageAreaRequestDTO;
import org.codewhiskers.vetapp.dto.UsageArea.response.UsageAreaResponseDTO;

public interface IRestUsageAreaController extends IGenericRestController<UsageAreaRequestDTO, UsageAreaResponseDTO, Long> {
} 