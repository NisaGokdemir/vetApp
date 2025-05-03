package org.codewhiskers.vetapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.dto.VaccinationInventory.request.VaccinationInventoryRequestDTO;
import org.codewhiskers.vetapp.dto.VaccinationInventory.response.VaccinationInventoryResponseDTO;
import org.codewhiskers.vetapp.entity.Clinic;
import org.codewhiskers.vetapp.entity.Vaccination;
import org.codewhiskers.vetapp.entity.VaccinationInventory;
import org.codewhiskers.vetapp.exception.BaseException;
import org.codewhiskers.vetapp.exception.ErrorMessage;
import org.codewhiskers.vetapp.exception.MessageType;
import org.codewhiskers.vetapp.mapper.VaccinationInventoryMapper;
import org.codewhiskers.vetapp.repository.ClinicRepository;
import org.codewhiskers.vetapp.repository.VaccinationInventoryRepository;
import org.codewhiskers.vetapp.repository.VaccinationRepository;
import org.codewhiskers.vetapp.service.IVaccinationInventoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VaccinationInventoryServiceImpl implements IVaccinationInventoryService {

    private final VaccinationInventoryRepository repository;
    private final VaccinationInventoryMapper mapper;
    private final VaccinationRepository vaccinationRepository;
    private final ClinicRepository clinicRepository;

    private VaccinationInventory findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST, "VaccinationInventory ID: " + id))
                );
    }

    private Vaccination findVaccinationById(Long id) {
        return vaccinationRepository.findById(id)
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST, "Vaccination ID: " + id))
                );
    }

    private Clinic findClinicById(Long id) {
        return clinicRepository.findById(id)
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST, "Clinic ID: " + id))
                );
    }

    @Override
    @Transactional
    public VaccinationInventoryResponseDTO create(VaccinationInventoryRequestDTO requestDTO) {
        Vaccination vaccination = findVaccinationById(requestDTO.getVaccinationId());
        Clinic clinic = findClinicById(requestDTO.getClinicId());

        VaccinationInventory inventory = mapper.toEntity(requestDTO, vaccination, clinic);
        VaccinationInventory savedInventory = repository.save(inventory);
        return mapper.toDto(savedInventory);
    }

    @Override
    @Transactional(readOnly = true)
    public VaccinationInventoryResponseDTO getById(Long id) {
        VaccinationInventory inventory = findById(id);
        return mapper.toDto(inventory);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<VaccinationInventoryResponseDTO> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDto);
    }

    @Override
    @Transactional
    public VaccinationInventoryResponseDTO update(Long id, VaccinationInventoryRequestDTO requestDTO) {
        VaccinationInventory existingInventory = findById(id);
        Vaccination vaccination = findVaccinationById(requestDTO.getVaccinationId());
        Clinic clinic = findClinicById(requestDTO.getClinicId());

        mapper.toUpdateEntity(requestDTO, existingInventory, vaccination, clinic);
        VaccinationInventory updatedInventory = repository.save(existingInventory);
        return mapper.toDto(updatedInventory);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        VaccinationInventory inventory = findById(id);
        repository.delete(inventory);
    }
} 