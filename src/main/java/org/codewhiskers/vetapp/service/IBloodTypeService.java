package org.codewhiskers.vetapp.service;

import org.codewhiskers.vetapp.dto.bloodType.request.BloodTypeRequestDTO;
import org.codewhiskers.vetapp.dto.bloodType.response.BloodTypeResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBloodTypeService {
    BloodTypeResponseDTO createBloodType(BloodTypeRequestDTO bloodTypeRequestDTO);

    BloodTypeResponseDTO getBloodTypeById(Long id);

    Page<BloodTypeResponseDTO> getAllBloodTypes(Pageable pageable);

    BloodTypeResponseDTO updateBloodType(Long id, BloodTypeRequestDTO bloodTypeRequestDTO);

    void deleteBloodType(Long id);
}
