package org.codewhiskers.vetapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.dto.Medication.request.MedicationRequestDTO;
import org.codewhiskers.vetapp.dto.Medication.response.MedicationResponseDTO;
import org.codewhiskers.vetapp.entity.Clinic;
import org.codewhiskers.vetapp.entity.Drug;
import org.codewhiskers.vetapp.entity.Medication;
import org.codewhiskers.vetapp.exception.BaseException;
import org.codewhiskers.vetapp.exception.ErrorMessage;
import org.codewhiskers.vetapp.exception.MessageType;
import org.codewhiskers.vetapp.mapper.MedicationMapper;
import org.codewhiskers.vetapp.repository.ClinicRepository;
import org.codewhiskers.vetapp.repository.DrugRepository;
import org.codewhiskers.vetapp.repository.MedicationRepository;
import org.codewhiskers.vetapp.service.IMedicationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MedicationServiceImpl implements IMedicationService {

    private final MedicationRepository medicationRepository;
    private final MedicationMapper medicationMapper;
    private final DrugRepository drugRepository;
    private final ClinicRepository clinicRepository;

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
    public MedicationResponseDTO create(MedicationRequestDTO medicationRequestDTO) {
        Drug drug = findDrugById(medicationRequestDTO.getDrugId());
        Clinic clinic = findClinicById(medicationRequestDTO.getClinicId());
        
        Medication medication = medicationMapper.toEntity(medicationRequestDTO, drug, clinic);
        Medication savedMedication = medicationRepository.save(medication);
        return medicationMapper.toDto(savedMedication);
    }

    @Override
    @Transactional(readOnly = true)
    public MedicationResponseDTO getById(Long id) {
        Medication medication = findMedicationById(id);
        return medicationMapper.toDto(medication);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<MedicationResponseDTO> getAll(Pageable pageable) {
        return medicationRepository.findAll(pageable)
                .map(medicationMapper::toDto);
    }

    @Override
    @Transactional
    public MedicationResponseDTO update(Long id, MedicationRequestDTO medicationRequestDTO) {
        Medication existingMedication = findMedicationById(id);
        Drug drug = findDrugById(medicationRequestDTO.getDrugId());
        Clinic clinic = findClinicById(medicationRequestDTO.getClinicId());
        
        medicationMapper.toUpdateEntity(medicationRequestDTO, existingMedication, drug, clinic);
        Medication updatedMedication = medicationRepository.save(existingMedication);
        return medicationMapper.toDto(updatedMedication);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Medication medication = findMedicationById(id);
        medicationRepository.delete(medication);
    }
}