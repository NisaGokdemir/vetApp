package org.codewhiskers.vetapp.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.dto.bloodType.request.BloodTypeRequestDTO;
import org.codewhiskers.vetapp.dto.bloodType.response.BloodTypeResponseDTO;
import org.codewhiskers.vetapp.entity.BloodType;
import org.codewhiskers.vetapp.entity.Species;
import org.codewhiskers.vetapp.exception.BaseException;
import org.codewhiskers.vetapp.exception.ErrorMessage;
import org.codewhiskers.vetapp.exception.MessageType;
import org.codewhiskers.vetapp.mapper.BloodTypeMapper;
import org.codewhiskers.vetapp.mapper.SpeciesMapper;
import org.codewhiskers.vetapp.repository.BloodTypeRepository;
import org.codewhiskers.vetapp.repository.SpeciesRepository;
import org.codewhiskers.vetapp.service.IBloodTypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BloodTypeServiceImpl implements IBloodTypeService {

    private final BloodTypeRepository bloodTypeRepository;
    private final SpeciesRepository speciesRepository;
    private final BloodTypeMapper bloodTypeMapper;

    private Species findSpeciesById(Long id) {
        return speciesRepository.findById(id)
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST, "Species ID: " + id))
                );
    }

    private BloodType findBloodTypeById(Long id) {
        return bloodTypeRepository.findById(id)
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST, "BloodType ID: " + id))
                );    }

    @Transactional
    @Override
    public BloodTypeResponseDTO createBloodType(BloodTypeRequestDTO bloodTypeRequestDTO) {
        Species species = findSpeciesById(bloodTypeRequestDTO.getSpeciesId());
        BloodType bloodType = bloodTypeMapper.toBloodTypeEntity(bloodTypeRequestDTO, species);
        bloodType = bloodTypeRepository.save(bloodType);
        return bloodTypeMapper.toBloodTypeResponseDto(bloodType);
    }

    @Override
    public BloodTypeResponseDTO getBloodTypeById(Long id) {
        BloodType bloodType = findBloodTypeById(id);
        return bloodTypeMapper.toBloodTypeResponseDto(bloodType);
    }

    @Override
    public Page<BloodTypeResponseDTO> getAllBloodTypes(Pageable pageable) {
        Page<BloodType> page = bloodTypeRepository.findAll(pageable);
        if (page.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.RECORDS_NOT_FOUND, "No bloodTypes Found"));
        }
        return page.map(bloodTypeMapper::toBloodTypeResponseDto);
    }

    @Transactional
    @Override
    public BloodTypeResponseDTO updateBloodType(Long id, BloodTypeRequestDTO bloodTypeRequestDTO) {
        Species species = findSpeciesById(bloodTypeRequestDTO.getSpeciesId());
        BloodType bloodType = findBloodTypeById(id);
        bloodTypeMapper.updateBloodTypeEntity(bloodTypeRequestDTO, bloodType, species);
        bloodType = bloodTypeRepository.save(bloodType);
        return bloodTypeMapper.toBloodTypeResponseDto(bloodType);
    }

    @Transactional
    @Override
    public void deleteBloodType(Long id) {
        BloodType bloodType = findBloodTypeById(id);
        bloodTypeRepository.delete(bloodType);
    }
}
