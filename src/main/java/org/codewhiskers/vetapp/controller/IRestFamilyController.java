package org.codewhiskers.vetapp.controller;

import org.codewhiskers.vetapp.dto.Family.request.FamilyRequestDTO;
import org.codewhiskers.vetapp.dto.Family.response.FamilyResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface IRestFamilyController {
    ResponseEntity<FamilyResponseDTO> createFamily(FamilyRequestDTO familyRequestDTO);
    ResponseEntity<FamilyResponseDTO> getFamilyById(Long id);
    ResponseEntity<FamilyResponseDTO> updateFamily(Long id, FamilyRequestDTO familyRequestDTO);
    void deleteFamily(Long id);
    ResponseEntity<Page<FamilyResponseDTO>> getAllFamilies(int page, int size);
}
