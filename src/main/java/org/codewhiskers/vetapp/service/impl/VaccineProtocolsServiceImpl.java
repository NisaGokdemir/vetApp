package org.codewhiskers.vetapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.dto.VaccineProtocols.request.VaccineProtocolsRequestDTO;
import org.codewhiskers.vetapp.dto.VaccineProtocols.response.VaccineProtocolsResponseDTO;
import org.codewhiskers.vetapp.entity.Species;
import org.codewhiskers.vetapp.entity.Vaccination;
import org.codewhiskers.vetapp.entity.VaccineProtocols;
import org.codewhiskers.vetapp.exception.BaseException;
import org.codewhiskers.vetapp.exception.ErrorMessage;
import org.codewhiskers.vetapp.exception.MessageType;
import org.codewhiskers.vetapp.mapper.VaccineProtocolsMapper;
import org.codewhiskers.vetapp.repository.SpeciesRepository;
import org.codewhiskers.vetapp.repository.VaccinationRepository;
import org.codewhiskers.vetapp.repository.VaccineProtocolsRepository;
import org.codewhiskers.vetapp.service.IVaccineProtocolsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VaccineProtocolsServiceImpl implements IVaccineProtocolsService {

    private final VaccineProtocolsRepository repository;
    private final VaccineProtocolsMapper mapper;
    private final VaccinationRepository vaccinationRepository;
    private final SpeciesRepository speciesRepository;

    private VaccineProtocols findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST, "VaccineProtocol ID: " + id))
                );
    }

    private Vaccination findVaccinationById(Long id) {
        return vaccinationRepository.findById(id)
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST, "Vaccination ID: " + id))
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
    public VaccineProtocolsResponseDTO create(VaccineProtocolsRequestDTO requestDTO) {
        Vaccination vaccination = findVaccinationById(requestDTO.getVaccinationId());
        Species species = findSpeciesById(requestDTO.getSpeciesId());

        VaccineProtocols protocol = mapper.toEntity(requestDTO, vaccination, species);
        VaccineProtocols savedProtocol = repository.save(protocol);
        return mapper.toDto(savedProtocol);
    }

    @Override
    @Transactional(readOnly = true)
    public VaccineProtocolsResponseDTO getById(Long id) {
        VaccineProtocols protocol = findById(id);
        return mapper.toDto(protocol);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<VaccineProtocolsResponseDTO> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDto);
    }

    @Override
    @Transactional
    public VaccineProtocolsResponseDTO update(Long id, VaccineProtocolsRequestDTO requestDTO) {
        VaccineProtocols existingProtocol = findById(id);
        Vaccination vaccination = findVaccinationById(requestDTO.getVaccinationId());
        Species species = findSpeciesById(requestDTO.getSpeciesId());

        mapper.toUpdateEntity(requestDTO, existingProtocol, vaccination, species);
        VaccineProtocols updatedProtocol = repository.save(existingProtocol);
        return mapper.toDto(updatedProtocol);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        VaccineProtocols protocol = findById(id);
        repository.delete(protocol);
    }
} 