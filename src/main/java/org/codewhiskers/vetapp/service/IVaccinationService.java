package org.codewhiskers.vetapp.service;

import org.codewhiskers.vetapp.common.service.IGenericService;
import org.codewhiskers.vetapp.dto.Vaccination.request.VaccinationRequestDTO;
import org.codewhiskers.vetapp.dto.Vaccination.response.VaccinationResponseDTO;
import org.codewhiskers.vetapp.dto.bloodType.request.BloodTypeRequestDTO;
import org.codewhiskers.vetapp.dto.bloodType.response.BloodTypeResponseDTO;

public interface IVaccinationService extends IGenericService<VaccinationRequestDTO, VaccinationResponseDTO, Long> {
}