package org.codewhiskers.vetapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.dto.Family.request.FamilyRequestDTO;
import org.codewhiskers.vetapp.dto.Family.response.FamilyResponseDTO;
import org.codewhiskers.vetapp.entity.Family;
import org.codewhiskers.vetapp.exception.BaseException;
import org.codewhiskers.vetapp.exception.ErrorMessage;
import org.codewhiskers.vetapp.exception.MessageType;
import org.codewhiskers.vetapp.mapper.FamilyMapper;
import org.codewhiskers.vetapp.repository.FamilyRepository;
import org.codewhiskers.vetapp.service.IFamilyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FamilyServiceImpl implements IFamilyService {
    private final FamilyMapper familyMapper;
    private final FamilyRepository familyRepository;

    private Family findFamilyById(Long id) {
        return familyRepository.findById(id).orElseThrow(
                () -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,id.toString()))
        );
    }

    @Override
    public FamilyResponseDTO createFamily(FamilyRequestDTO familyRequestDTO) {
        Family family = familyMapper.toFamilyEntity(familyRequestDTO);
        Family savedFamily = familyRepository.save(family);
        return familyMapper.toFamilyResponseDto(savedFamily);
    }

    @Override
    public FamilyResponseDTO getFamilyById(Long id) {
        Family family = findFamilyById(id);
        FamilyResponseDTO familyResponseDTO = familyMapper.toFamilyResponseDto(family);
        return familyMapper.toFamilyResponseDto(family);
    }

    @Override
    public FamilyResponseDTO updateFamily(Long id, FamilyRequestDTO familyRequestDTO) {
        Family family = findFamilyById(id);
        familyMapper.updateFamilyEntity(familyRequestDTO, family);
        Family updatedFamily = familyRepository.save(family);
        if (updatedFamily.getName() == null || updatedFamily.getName().isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.RECORD_CREATE_UNSUCCESS, updatedFamily.getName()));
        }
        return familyMapper.toFamilyResponseDto(updatedFamily);
    }

    @Override
    public void deleteFamily(Long id) {
        Family family = findFamilyById(id);
        familyRepository.delete(family);
    }

    @Override
    public Page<FamilyResponseDTO> getAllFamilies(int page, int size) {
        Page<Family> families = familyRepository.findAll(PageRequest.of(page, size));
        if (!families.hasContent()) {
            throw new BaseException(new ErrorMessage(MessageType.RECORDS_NOT_FOUND, ""));
        }
        return families.map(familyMapper::toFamilyResponseDto);
    }
}
