package org.codewhiskers.vetapp.service;

import org.codewhiskers.vetapp.dto.Owner.request.OwnerRequestDTO;
import org.codewhiskers.vetapp.dto.Owner.response.OwnerResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOwnerService {

    OwnerResponseDTO createOwner(OwnerRequestDTO ownerRequestDTO);

    OwnerResponseDTO getOwnerById(Long id);

    Page<OwnerResponseDTO> getAllOwners(Pageable pageable);

    OwnerResponseDTO updateOwner(Long id, OwnerRequestDTO ownerRequestDTO);

    void deleteOwner(Long id);
}
