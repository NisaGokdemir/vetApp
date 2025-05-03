package org.codewhiskers.vetapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.dto.MedicationBatch.request.MedicationBatchRequestDTO;
import org.codewhiskers.vetapp.dto.MedicationBatch.response.MedicationBatchResponseDTO;
import org.codewhiskers.vetapp.entity.Clinic;
import org.codewhiskers.vetapp.entity.Drug;
import org.codewhiskers.vetapp.entity.Medication;
import org.codewhiskers.vetapp.entity.MedicationBatch;
import org.codewhiskers.vetapp.exception.BaseException;
import org.codewhiskers.vetapp.exception.ErrorMessage;
import org.codewhiskers.vetapp.exception.MessageType;
import org.codewhiskers.vetapp.mapper.MedicationBatchMapper;
import org.codewhiskers.vetapp.repository.ClinicRepository;
import org.codewhiskers.vetapp.repository.DrugRepository;
import org.codewhiskers.vetapp.repository.MedicationBatchRepository;
import org.codewhiskers.vetapp.repository.MedicationRepository;
import org.codewhiskers.vetapp.service.IMedicationBatchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MedicationBatchServiceImpl implements IMedicationBatchService {

    private final MedicationBatchRepository repository;
    private final MedicationBatchMapper mapper;
    private final MedicationRepository medicationRepository;
    private final DrugRepository drugRepository;
    private final ClinicRepository clinicRepository;

    private MedicationBatch findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST, "MedicationBatch ID: " + id))
                );
    }

    private Medication findMedicationById(Long id) {
        return medicationRepository.findById(id)
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST, "Medication ID: " + id))
                );
    }

    private Drug findDrugById(Long id) {
        return drugRepository.findById(id)
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST, "Drug ID: " + id))
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
    public MedicationBatchResponseDTO create(MedicationBatchRequestDTO requestDTO) {
        Medication medication = findMedicationById(requestDTO.getMedicationId());
        Drug drug = findDrugById(requestDTO.getDrugId());
        Clinic clinic = findClinicById(requestDTO.getClinicId());

        if (requestDTO.getQuantity() == null || requestDTO.getQuantity() <= 0) {
            throw new BaseException(new ErrorMessage(MessageType.INVALID_INPUT, "Miktar 0'dan büyük olmalıdır"));
        }

        MedicationBatch batch = mapper.toEntity(requestDTO, medication, drug, clinic);
        MedicationBatch savedBatch = repository.save(batch);
        return mapper.toDto(savedBatch);
    }

    @Override
    @Transactional(readOnly = true)
    public MedicationBatchResponseDTO getById(Long id) {
        MedicationBatch batch = findById(id);
        return mapper.toDto(batch);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<MedicationBatchResponseDTO> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDto);
    }

    @Override
    @Transactional
    public MedicationBatchResponseDTO update(Long id, MedicationBatchRequestDTO requestDTO) {
        MedicationBatch existingBatch = findById(id);
        Medication medication = findMedicationById(requestDTO.getMedicationId());
        Drug drug = findDrugById(requestDTO.getDrugId());
        Clinic clinic = findClinicById(requestDTO.getClinicId());

        if (requestDTO.getQuantity() == null || requestDTO.getQuantity() <= 0) {
            throw new BaseException(new ErrorMessage(MessageType.INVALID_INPUT, "Miktar 0'dan büyük olmalıdır"));
        }

        mapper.toUpdateEntity(requestDTO, existingBatch, medication, drug, clinic);
        MedicationBatch updatedBatch = repository.save(existingBatch);
        return mapper.toDto(updatedBatch);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        MedicationBatch batch = findById(id);
        repository.delete(batch);
    }
}
