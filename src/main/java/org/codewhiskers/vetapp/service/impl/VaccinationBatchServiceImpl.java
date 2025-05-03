package org.codewhiskers.vetapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.dto.VaccinationBatches.request.VaccinationBatchesRequestDTO;
import org.codewhiskers.vetapp.dto.VaccinationBatches.response.VaccinationBatchesResponseDTO;
import org.codewhiskers.vetapp.entity.Clinic;
import org.codewhiskers.vetapp.entity.Vaccination;
import org.codewhiskers.vetapp.entity.VaccinationBatch;
import org.codewhiskers.vetapp.entity.VaccinationInventory;
import org.codewhiskers.vetapp.exception.BaseException;
import org.codewhiskers.vetapp.exception.ErrorMessage;
import org.codewhiskers.vetapp.exception.MessageType;
import org.codewhiskers.vetapp.mapper.VaccinationBatchMapper;
import org.codewhiskers.vetapp.repository.ClinicRepository;
import org.codewhiskers.vetapp.repository.VaccinationBatchesRepository;
import org.codewhiskers.vetapp.repository.VaccinationInventoryRepository;
import org.codewhiskers.vetapp.repository.VaccinationRepository;
import org.codewhiskers.vetapp.service.IVaccinationBatchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VaccinationBatchServiceImpl implements IVaccinationBatchService {

    private final VaccinationBatchesRepository repository;
    private final VaccinationBatchMapper mapper;
    private final VaccinationRepository vaccinationRepository;
    private final VaccinationInventoryRepository vaccinationInventoryRepository;
    private final ClinicRepository clinicRepository;

    private VaccinationBatch findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST, "VaccinationBatch ID: " + id))
                );
    }

    private Vaccination findVaccinationById(Long id) {
        return vaccinationRepository.findById(id)
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST, "Vaccination ID: " + id))
                );
    }

    private VaccinationInventory findVaccinationInventoryById(Long id) {
        return vaccinationInventoryRepository.findById(id)
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST, "VaccinationInventory ID: " + id))
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
    public VaccinationBatchesResponseDTO create(VaccinationBatchesRequestDTO requestDTO) {
        Vaccination vaccination = findVaccinationById(requestDTO.getVaccinationId());
        VaccinationInventory vaccinationInventory = findVaccinationInventoryById(requestDTO.getVaccinationInventoryId());
        Clinic clinic = findClinicById(requestDTO.getClinicId());

        VaccinationBatch batch = mapper.toEntity(requestDTO, vaccinationInventory, vaccination, clinic);
        VaccinationBatch savedBatch = repository.save(batch);
        return mapper.toDto(savedBatch);
    }

    @Override
    @Transactional(readOnly = true)
    public VaccinationBatchesResponseDTO getById(Long id) {
        VaccinationBatch batch = findById(id);
        return mapper.toDto(batch);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<VaccinationBatchesResponseDTO> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDto);
    }

    @Override
    @Transactional
    public VaccinationBatchesResponseDTO update(Long id, VaccinationBatchesRequestDTO requestDTO) {
        VaccinationBatch existingBatch = findById(id);
        Vaccination vaccination = findVaccinationById(requestDTO.getVaccinationId());
        VaccinationInventory vaccinationInventory = findVaccinationInventoryById(requestDTO.getVaccinationInventoryId());
        Clinic clinic = findClinicById(requestDTO.getClinicId());

        mapper.toUpdateEntity(requestDTO, existingBatch, vaccinationInventory, vaccination, clinic);
        VaccinationBatch updatedBatch = repository.save(existingBatch);
        return mapper.toDto(updatedBatch);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        VaccinationBatch batch = findById(id);
        repository.delete(batch);
    }
} 