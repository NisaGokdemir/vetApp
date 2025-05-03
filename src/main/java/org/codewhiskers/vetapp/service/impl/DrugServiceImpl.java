package org.codewhiskers.vetapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.dto.Drug.request.DrugRequestDTO;
import org.codewhiskers.vetapp.dto.Drug.response.DrugResponseDTO;
import org.codewhiskers.vetapp.entity.Drug;
import org.codewhiskers.vetapp.exception.BaseException;
import org.codewhiskers.vetapp.exception.ErrorMessage;
import org.codewhiskers.vetapp.exception.MessageType;
import org.codewhiskers.vetapp.mapper.DrugMapper;
import org.codewhiskers.vetapp.repository.DrugRepository;
import org.codewhiskers.vetapp.service.IDrugService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DrugServiceImpl implements IDrugService {

    private final DrugRepository drugRepository;
    private final DrugMapper drugMapper;

    private Drug findDrugById(Long id) {
        return drugRepository.findById(id)
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST, "Drug ID: " + id))
                );
    }

    @Override
    public DrugResponseDTO create(DrugRequestDTO drugRequestDTO) {
        Drug drug = drugMapper.toEntity(drugRequestDTO);
        Drug savedDrug = drugRepository.save(drug);
        return drugMapper.toDto(savedDrug);
    }

    @Override
    public DrugResponseDTO getById(Long id) {
        Drug drug = findDrugById(id);
        return drugMapper.toDto(drug);
    }

    @Override
    public Page<DrugResponseDTO> getAll(Pageable pageable) {
        return drugRepository.findAll(pageable)
                .map(drugMapper::toDto);
    }

    @Override
    public DrugResponseDTO update(Long id, DrugRequestDTO drugRequestDTO) {
        Drug existingDrug = findDrugById(id);
        drugMapper.toUpdateEntity(drugRequestDTO, existingDrug);
        Drug updatedDrug = drugRepository.save(existingDrug);
        return drugMapper.toDto(updatedDrug);
    }

    @Override
    public void delete(Long id) {
        Drug drug = findDrugById(id);
        drugRepository.delete(drug);
    }
} 