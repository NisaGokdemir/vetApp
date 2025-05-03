package org.codewhiskers.vetapp.controller;

import jakarta.validation.Valid;
import org.codewhiskers.vetapp.common.service.IGenericRestController;
import org.codewhiskers.vetapp.dto.bloodType.request.BloodTypeRequestDTO;
import org.codewhiskers.vetapp.dto.bloodType.response.BloodTypeResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

/*
public interface IRestBloodTypeController {
    ResponseEntity<BloodTypeResponseDTO> createBloodType(@Valid BloodTypeRequestDTO bloodTypeRequestDTO);

    ResponseEntity<BloodTypeResponseDTO> getBloodTypeById(Long id);

    ResponseEntity<Page<BloodTypeResponseDTO>> getAllBloodTypes(Pageable pageable);

    ResponseEntity<BloodTypeResponseDTO> updateBloodType(Long id,@Valid BloodTypeRequestDTO bloodTypeRequestDTO);

    ResponseEntity<Void> deleteBloodType(Long id);
}
*/

public interface IRestBloodTypeController extends IGenericRestController<BloodTypeRequestDTO, BloodTypeResponseDTO, Long> {
}
