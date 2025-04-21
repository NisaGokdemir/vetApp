package org.codewhiskers.vetapp.controller;

import org.codewhiskers.vetapp.dto.Specialization.request.SpecializationRequestDTO;
import org.codewhiskers.vetapp.dto.Specialization.response.SpecializationResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface IRestSpecializationController {

    ResponseEntity<Page<SpecializationResponseDTO>> getAllSpecializations(int page, int size);

    ResponseEntity<SpecializationResponseDTO> createSpecialization(SpecializationRequestDTO specializationRequestDTO);

    ResponseEntity<SpecializationResponseDTO> updateSpecialization(Long id, SpecializationRequestDTO specializationRequestDTO);

    void deleteSpecialization(Long id);

    ResponseEntity<SpecializationResponseDTO> getSpecializationById(Long id);
}
