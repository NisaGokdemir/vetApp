package org.codewhiskers.vetapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.dto.DrugProtocol.request.DrugProtocolRequestDTO;
import org.codewhiskers.vetapp.dto.DrugProtocol.response.DrugProtocolResponseDTO;
import org.codewhiskers.vetapp.entity.Drug;
import org.codewhiskers.vetapp.entity.DrugProtocol;
import org.codewhiskers.vetapp.entity.Species;
import org.codewhiskers.vetapp.exception.BaseException;
import org.codewhiskers.vetapp.exception.ErrorMessage;
import org.codewhiskers.vetapp.exception.MessageType;
import org.codewhiskers.vetapp.mapper.DrugProtocolMapper;
import org.codewhiskers.vetapp.repository.DrugProtocolRepository;
import org.codewhiskers.vetapp.repository.DrugRepository;
import org.codewhiskers.vetapp.repository.SpeciesRepository;
import org.codewhiskers.vetapp.service.IDrugProtocolService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DrugProtocolServiceImpl implements IDrugProtocolService {

    private final DrugProtocolRepository repository;
    private final DrugProtocolMapper mapper;
    private final DrugRepository drugRepository;
    private final SpeciesRepository speciesRepository;

    private DrugProtocol findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST, "DrugProtocol ID: " + id))
                );
    }

    private Drug findDrugById(Long id) {
        return drugRepository.findById(id)
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST, "Drug ID: " + id))
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
    public DrugProtocolResponseDTO create(DrugProtocolRequestDTO requestDTO) {
        Drug drug = findDrugById(requestDTO.getDrugId());
        Species species = findSpeciesById(requestDTO.getSpeciesId());
        DrugProtocol protocol = mapper.toEntity(requestDTO, drug, species);
        DrugProtocol savedProtocol = repository.save(protocol);
        return mapper.toDto(savedProtocol);
    }

    @Override
    @Transactional(readOnly = true)
    public DrugProtocolResponseDTO getById(Long id) {
        DrugProtocol protocol = findById(id);
        return mapper.toDto(protocol);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DrugProtocolResponseDTO> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDto);
    }

    @Override
    @Transactional
    public DrugProtocolResponseDTO update(Long id, DrugProtocolRequestDTO requestDTO) {
        DrugProtocol existingProtocol = findById(id);
        Drug drug = findDrugById(requestDTO.getDrugId());
        Species species = findSpeciesById(requestDTO.getSpeciesId());
        mapper.toUpdateEntity(requestDTO, existingProtocol, drug, species);
        DrugProtocol updatedProtocol = repository.save(existingProtocol);
        return mapper.toDto(updatedProtocol);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        DrugProtocol protocol = findById(id);
        repository.delete(protocol);
    }
}
