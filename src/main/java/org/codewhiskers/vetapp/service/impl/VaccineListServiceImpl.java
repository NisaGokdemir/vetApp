package org.codewhiskers.vetapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.dto.VaccineList.request.VaccineListRequestDTO;
import org.codewhiskers.vetapp.dto.VaccineList.response.VaccineListResponseDTO;
import org.codewhiskers.vetapp.entity.Species;
import org.codewhiskers.vetapp.entity.Vaccination;
import org.codewhiskers.vetapp.entity.VaccineList;
import org.codewhiskers.vetapp.entity.VaccineType;
import org.codewhiskers.vetapp.exception.BaseException;
import org.codewhiskers.vetapp.exception.ErrorMessage;
import org.codewhiskers.vetapp.exception.MessageType;
import org.codewhiskers.vetapp.mapper.VaccineListMapper;
import org.codewhiskers.vetapp.repository.SpeciesRepository;
import org.codewhiskers.vetapp.repository.VaccinationRepository;
import org.codewhiskers.vetapp.repository.VaccineListRepository;
import org.codewhiskers.vetapp.repository.VaccineTypeRepository;
import org.codewhiskers.vetapp.service.IVaccineListService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class VaccineListServiceImpl implements IVaccineListService {

    private final VaccineListRepository repository;
    private final VaccinationRepository vaccinationRepository;
    private final SpeciesRepository speciesRepository;
    private final VaccineTypeRepository vaccineTypeRepository;
    
    @Qualifier("vaccineListMapperImpl")
    private final VaccineListMapper mapper;

    private VaccineList findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> {
                    ErrorMessage errorMessage = new ErrorMessage(MessageType.NO_RECORD_EXIST, "Aşı listesi bulunamadı. ID: " + id);
                    return new BaseException(errorMessage);
                });
    }

    private Vaccination findVaccinationById(Long id) {
        return vaccinationRepository.findById(id)
                .orElseThrow(() -> {
                    ErrorMessage errorMessage = new ErrorMessage(MessageType.NO_RECORD_EXIST, "Aşı bulunamadı. ID: " + id);
                    return new BaseException(errorMessage);
                });
    }

    private Species findSpeciesById(Long id) {
        return speciesRepository.findById(id)
                .orElseThrow(() -> {
                    ErrorMessage errorMessage = new ErrorMessage(MessageType.NO_RECORD_EXIST, "Tür bulunamadı. ID: " + id);
                    return new BaseException(errorMessage);
                });
    }

    private VaccineType findVaccineTypeById(Long id) {
        return vaccineTypeRepository.findById(id)
                .orElseThrow(() -> {
                    ErrorMessage errorMessage = new ErrorMessage(MessageType.NO_RECORD_EXIST, "Aşı tipi bulunamadı. ID: " + id);
                    return new BaseException(errorMessage);
                });
    }

    @Override
    public VaccineListResponseDTO create(VaccineListRequestDTO requestDTO) {
        Vaccination vaccination = findVaccinationById(requestDTO.getVaccineId());
        Species species = findSpeciesById(requestDTO.getSpeciesId());
        VaccineType vaccineType = findVaccineTypeById(requestDTO.getVaccineTypeId());

        VaccineList vaccineList = mapper.toEntity(requestDTO, vaccination, species, vaccineType);
        return mapper.toDto(repository.save(vaccineList));
    }

    @Override
    public VaccineListResponseDTO getById(Long id) {
        return mapper.toDto(findById(id));
    }

    @Override
    public Page<VaccineListResponseDTO> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDto);
    }

    @Override
    public VaccineListResponseDTO update(Long id, VaccineListRequestDTO requestDTO) {
        VaccineList vaccineList = findById(id);
        Vaccination vaccination = findVaccinationById(requestDTO.getVaccineId());
        Species species = findSpeciesById(requestDTO.getSpeciesId());
        VaccineType vaccineType = findVaccineTypeById(requestDTO.getVaccineTypeId());

        mapper.toUpdateEntity(requestDTO, vaccineList, vaccination, species, vaccineType);
        return mapper.toDto(repository.save(vaccineList));
    }

    @Override
    public void delete(Long id) {
        repository.delete(findById(id));
    }
} 