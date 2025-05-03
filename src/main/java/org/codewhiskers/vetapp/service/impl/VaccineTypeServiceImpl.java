package org.codewhiskers.vetapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.dto.VaccineType.request.VaccineTypeRequestDTO;
import org.codewhiskers.vetapp.dto.VaccineType.response.VaccineTypeResponseDTO;
import org.codewhiskers.vetapp.entity.VaccineType;
import org.codewhiskers.vetapp.exception.BaseException;
import org.codewhiskers.vetapp.exception.ErrorMessage;
import org.codewhiskers.vetapp.exception.MessageType;
import org.codewhiskers.vetapp.mapper.VaccineTypeMapper;
import org.codewhiskers.vetapp.repository.VaccineTypeRepository;
import org.codewhiskers.vetapp.service.IVaccineTypeService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class VaccineTypeServiceImpl implements IVaccineTypeService {

    private final VaccineTypeRepository repository;
    
    @Qualifier("vaccineTypeMapperImpl")
    private final VaccineTypeMapper mapper;

    private VaccineType findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> {
                    ErrorMessage errorMessage = new ErrorMessage(MessageType.NO_RECORD_EXIST, "Aşı tipi bulunamadı. ID: " + id);
                    return new BaseException(errorMessage);
                });
    }

    @Override
    public VaccineTypeResponseDTO create(VaccineTypeRequestDTO requestDTO) {
        VaccineType vaccineType = mapper.toEntity(requestDTO);
        return mapper.toDto(repository.save(vaccineType));
    }

    @Override
    public VaccineTypeResponseDTO getById(Long id) {
        return mapper.toDto(findById(id));
    }

    @Override
    public Page<VaccineTypeResponseDTO> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDto);
    }

    @Override
    public VaccineTypeResponseDTO update(Long id, VaccineTypeRequestDTO requestDTO) {
        VaccineType vaccineType = findById(id);
        mapper.toUpdateEntity(requestDTO, vaccineType);
        return mapper.toDto(repository.save(vaccineType));
    }

    @Override
    public void delete(Long id) {
        repository.delete(findById(id));
    }
} 