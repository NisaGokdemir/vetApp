package org.codewhiskers.vetapp.controller;

import org.codewhiskers.vetapp.common.service.IGenericRestController;
import org.codewhiskers.vetapp.dto.Vaccination.request.VaccinationRequestDTO;
import org.codewhiskers.vetapp.dto.Vaccination.response.VaccinationResponseDTO;

public interface IRestVaccinationController extends IGenericRestController<VaccinationRequestDTO, VaccinationResponseDTO, Long> {
}
