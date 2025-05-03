package org.codewhiskers.vetapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.dto.UsageArea.request.UsageAreaRequestDTO;
import org.codewhiskers.vetapp.dto.UsageArea.response.UsageAreaResponseDTO;
import org.codewhiskers.vetapp.entity.UsageArea;
import org.codewhiskers.vetapp.exception.BaseException;
import org.codewhiskers.vetapp.exception.ErrorMessage;
import org.codewhiskers.vetapp.exception.MessageType;
import org.codewhiskers.vetapp.mapper.UsageAreaMapper;
import org.codewhiskers.vetapp.repository.UsageAreaRepository;
import org.codewhiskers.vetapp.service.IUsageAreaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsageAreaServiceImpl implements IUsageAreaService {

    private final UsageAreaRepository repository;
    private final UsageAreaMapper mapper;

    private UsageArea findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST, "UsageArea ID: " + id))
                );
    }

    @Override
    @Transactional
    public UsageAreaResponseDTO create(UsageAreaRequestDTO requestDTO) {
        UsageArea usageArea = mapper.toEntity(requestDTO);
        UsageArea savedUsageArea = repository.save(usageArea);
        return mapper.toDto(savedUsageArea);
    }

    @Override
    @Transactional(readOnly = true)
    public UsageAreaResponseDTO getById(Long id) {
        UsageArea usageArea = findById(id);
        return mapper.toDto(usageArea);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UsageAreaResponseDTO> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDto);
    }

    @Override
    @Transactional
    public UsageAreaResponseDTO update(Long id, UsageAreaRequestDTO requestDTO) {
        UsageArea existingUsageArea = findById(id);
        mapper.toUpdateEntity(requestDTO, existingUsageArea);
        UsageArea updatedUsageArea = repository.save(existingUsageArea);
        return mapper.toDto(updatedUsageArea);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        UsageArea usageArea = findById(id);
        repository.delete(usageArea);
    }
} 