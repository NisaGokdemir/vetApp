package org.codewhiskers.vetapp.service;

import org.codewhiskers.vetapp.dto.Family.request.FamilyRequestDTO;
import org.codewhiskers.vetapp.dto.Family.response.FamilyResponseDTO;
import org.springframework.data.domain.Page;


public interface IFamilyService {
    FamilyResponseDTO createFamily(FamilyRequestDTO familyRequestDTO);
    FamilyResponseDTO getFamilyById(Long id);
    FamilyResponseDTO updateFamily(Long id, FamilyRequestDTO familyRequestDTO);
    void deleteFamily(Long id);
    Page<FamilyResponseDTO> getAllFamilies(int page, int size);
}
