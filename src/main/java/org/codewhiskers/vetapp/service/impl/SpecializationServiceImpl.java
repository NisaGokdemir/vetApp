package org.codewhiskers.vetapp.service.impl;

import org.codewhiskers.vetapp.dto.Specialization.request.SpecializationRequestDTO;
import org.codewhiskers.vetapp.dto.Specialization.response.SpecializationResponseDTO;
import org.codewhiskers.vetapp.service.ISpecializationService;
import org.springframework.data.domain.Page;

public class SpecializationServiceImpl implements ISpecializationService {
    @Override
    public Page<SpecializationResponseDTO> getAllSpecializations(int page, int size) {
        return null;
    }

    @Override
    public SpecializationResponseDTO createSpecialization(SpecializationRequestDTO specializationRequestDTO) {
        return null;
    }

    @Override
    public SpecializationResponseDTO updateSpecialization(Long id, SpecializationRequestDTO specializationRequestDTO) {
        return null;
    }

    @Override
    public void deleteSpecialization(Long id) {

    }

    @Override
    public SpecializationResponseDTO getSpecializationById(Long id) {
        return null;
    }
}
