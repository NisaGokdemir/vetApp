package org.codewhiskers.vetapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.dto.DrugList.request.DrugListRequestDTO;
import org.codewhiskers.vetapp.dto.DrugList.response.DrugListResponseDTO;
import org.codewhiskers.vetapp.entity.Drug;
import org.codewhiskers.vetapp.entity.DrugList;
import org.codewhiskers.vetapp.entity.Species;
import org.codewhiskers.vetapp.entity.UsageArea;
import org.codewhiskers.vetapp.exception.BaseException;
import org.codewhiskers.vetapp.exception.ErrorMessage;
import org.codewhiskers.vetapp.exception.MessageType;
import org.codewhiskers.vetapp.mapper.DrugListMapper;
import org.codewhiskers.vetapp.repository.DrugListRepository;
import org.codewhiskers.vetapp.repository.DrugRepository;
import org.codewhiskers.vetapp.repository.SpeciesRepository;
import org.codewhiskers.vetapp.repository.UsageAreaRepository;
import org.codewhiskers.vetapp.service.IDrugListService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DrugListServiceImpl implements IDrugListService {

    private final DrugListRepository repository;
    @Qualifier("drugListMapperImpl")
    private final DrugListMapper mapper;
    private final DrugRepository drugRepository;
    private final UsageAreaRepository usageAreaRepository;
    private final SpeciesRepository speciesRepository;

    private DrugList findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST, "DrugList ID: " + id))
                );
    }

    private Drug findDrugById(Long id) {
        return drugRepository.findById(id)
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST, "Drug ID: " + id))
                );
    }

    private UsageArea findUsageAreaById(Long id) {
        return usageAreaRepository.findById(id)
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST, "UsageArea ID: " + id))
                );
    }

    private Species findSpeciesById(Long id) {
        return speciesRepository.findById(id)
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST, "Species ID: " + id))
                );
    }

    @Override
    @Transactional
    public DrugListResponseDTO create(DrugListRequestDTO requestDTO) {
        Drug drug = findDrugById(requestDTO.getDrugId());
        UsageArea usageArea = findUsageAreaById(requestDTO.getUsageAreaId());
        Species species = findSpeciesById(requestDTO.getSpeciesId());
        
        DrugList drugList = mapper.toEntity(requestDTO, drug, usageArea, species);
        DrugList savedDrugList = repository.save(drugList);
        return mapper.toDto(savedDrugList);
    }

    @Override
    @Transactional(readOnly = true)
    public DrugListResponseDTO getById(Long id) {
        DrugList drugList = findById(id);
        return mapper.toDto(drugList);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DrugListResponseDTO> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDto);
    }

    @Override
    @Transactional
    public DrugListResponseDTO update(Long id, DrugListRequestDTO requestDTO) {
        DrugList existingDrugList = findById(id);
        Drug drug = findDrugById(requestDTO.getDrugId());
        UsageArea usageArea = findUsageAreaById(requestDTO.getUsageAreaId());
        Species species = findSpeciesById(requestDTO.getSpeciesId());
        
        mapper.toUpdateEntity(requestDTO, existingDrugList, drug, usageArea, species);
        DrugList updatedDrugList = repository.save(existingDrugList);
        return mapper.toDto(updatedDrugList);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        DrugList drugList = findById(id);
        repository.delete(drugList);
    }
} 