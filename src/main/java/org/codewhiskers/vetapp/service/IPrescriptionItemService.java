package org.codewhiskers.vetapp.service;

import org.codewhiskers.vetapp.dto.PrescriptionItem.request.PrescriptionItemRequestDTO;
import org.codewhiskers.vetapp.dto.PrescriptionItem.response.PrescriptionItemResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IPrescriptionItemService {

    Page<PrescriptionItemResponseDTO> getAllPrescriptionItems(Pageable pageable);

    PrescriptionItemResponseDTO getPrescriptionItemById(Long id);

    PrescriptionItemResponseDTO createPrescriptionItem(PrescriptionItemRequestDTO dto);

    PrescriptionItemResponseDTO updatePrescriptionItem(Long id, PrescriptionItemRequestDTO dto);

    void deletePrescriptionItem(Long id);

}
