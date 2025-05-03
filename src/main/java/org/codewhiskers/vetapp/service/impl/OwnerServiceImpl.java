package org.codewhiskers.vetapp.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.dto.Owner.request.OwnerRequestDTO;
import org.codewhiskers.vetapp.dto.Owner.response.OwnerResponseDTO;
import org.codewhiskers.vetapp.entity.Owner;
import org.codewhiskers.vetapp.entity.User;
import org.codewhiskers.vetapp.exception.BaseException;
import org.codewhiskers.vetapp.exception.ErrorMessage;
import org.codewhiskers.vetapp.exception.MessageType;
import org.codewhiskers.vetapp.mapper.OwnerMapper;
import org.codewhiskers.vetapp.repository.OwnerRepository;
import org.codewhiskers.vetapp.repository.UserRepository;
import org.codewhiskers.vetapp.service.IOwnerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements IOwnerService {

    private final OwnerRepository ownerRepository;
    private final OwnerMapper ownerMapper;

    private Owner findOwnerById(Long id) {
        return ownerRepository.findById(id)
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST, "Owner ID: " + id))
                );
    }

    @Override
    public OwnerResponseDTO createOwner(OwnerRequestDTO ownerRequestDTO) {
        Owner owner = ownerMapper.toOwnerEntity(ownerRequestDTO);

        owner = ownerRepository.save(owner);
        return ownerMapper.toOwnerResponseDto(owner);
    }

    @Override
    public OwnerResponseDTO getOwnerById(Long id) {
        Owner owner = findOwnerById(id);
        return ownerMapper.toOwnerResponseDto(owner);
    }

    @Override
    public Page<OwnerResponseDTO> getAllOwners(Pageable pageable) {
        Page<Owner> page = ownerRepository.findAll(pageable);
        if (page.isEmpty()) {
            throw new BaseException(
                    new ErrorMessage(MessageType.NO_RECORD_EXIST, "No owners found")
            );
        }
        return page.map(ownerMapper::toOwnerResponseDto);
    }

    @Transactional
    @Override
    public OwnerResponseDTO updateOwner(Long id, OwnerRequestDTO ownerRequestDTO) {
        Owner owner = findOwnerById(id);
        ownerMapper.updateOwnerEntity(ownerRequestDTO, owner);

        owner = ownerRepository.save(owner);
        return ownerMapper.toOwnerResponseDto(owner);
    }

    @Transactional
    @Override
    public void deleteOwner(Long id) {
        Owner owner = findOwnerById(id);
        ownerRepository.delete(owner);
    }
}
