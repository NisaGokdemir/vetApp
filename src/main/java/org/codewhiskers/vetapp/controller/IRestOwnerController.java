package org.codewhiskers.vetapp.controller;

import jakarta.validation.Valid;
import org.codewhiskers.vetapp.dto.Owner.request.OwnerRequestDTO;
import org.codewhiskers.vetapp.dto.Owner.response.OwnerResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface IRestOwnerController {

    ResponseEntity<OwnerResponseDTO> createOwner(@Valid OwnerRequestDTO ownerRequestDTO);

    ResponseEntity<OwnerResponseDTO> getOwnerById(Long id);

    ResponseEntity<Page<OwnerResponseDTO>> getAllOwners(Pageable pageable);

    ResponseEntity<OwnerResponseDTO> updateOwner(Long id, @Valid OwnerRequestDTO ownerRequestDTO);

    ResponseEntity<Void> deleteOwner(Long id);
}
