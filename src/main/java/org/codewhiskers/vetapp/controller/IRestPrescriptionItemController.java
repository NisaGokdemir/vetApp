package org.codewhiskers.vetapp.controller;

import org.codewhiskers.vetapp.dto.PrescriptionItem.request.PrescriptionItemRequestDTO;
import org.codewhiskers.vetapp.dto.PrescriptionItem.response.PrescriptionItemResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface IRestPrescriptionItemController {
    ResponseEntity<Page<PrescriptionItemResponseDTO>> getAllPrescriptionItems(Pageable pageable);

    ResponseEntity<PrescriptionItemResponseDTO> getPrescriptionItemById(Long id);

    ResponseEntity<PrescriptionItemResponseDTO> createPrescriptionItem(PrescriptionItemRequestDTO dto);

    ResponseEntity<PrescriptionItemResponseDTO> updatePrescriptionItem(Long id, PrescriptionItemRequestDTO dto);

    void deletePrescriptionItem(Long id);
}
