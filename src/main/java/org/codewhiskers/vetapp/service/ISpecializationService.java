package org.codewhiskers.vetapp.service;

import org.codewhiskers.vetapp.dto.Specialization.request.SpecializationRequestDTO;
import org.codewhiskers.vetapp.dto.Specialization.response.SpecializationResponseDTO;
import org.springframework.data.domain.Page;

public interface ISpecializationService {


    Page<SpecializationResponseDTO> getAllSpecializations(int page, int size);

    SpecializationResponseDTO createSpecialization(SpecializationRequestDTO specializationRequestDTO);

    SpecializationResponseDTO updateSpecialization(Long id, SpecializationRequestDTO specializationRequestDTO);

    void deleteSpecialization(Long id);

    SpecializationResponseDTO getSpecializationById(Long id);
}
