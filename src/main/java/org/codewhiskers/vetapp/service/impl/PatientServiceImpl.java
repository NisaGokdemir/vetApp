package org.codewhiskers.vetapp.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.dto.Patient.request.PatientRequestDTO;
import org.codewhiskers.vetapp.dto.Patient.response.PatientResponseDTO;
import org.codewhiskers.vetapp.dto.bloodType.request.BloodTypeRequestDTO;
import org.codewhiskers.vetapp.dto.bloodType.response.BloodTypeResponseDTO;
import org.codewhiskers.vetapp.entity.*;
import org.codewhiskers.vetapp.exception.BaseException;
import org.codewhiskers.vetapp.exception.ErrorMessage;
import org.codewhiskers.vetapp.exception.MessageType;
import org.codewhiskers.vetapp.mapper.BloodTypeMapper;
import org.codewhiskers.vetapp.mapper.PatientMapper;
import org.codewhiskers.vetapp.repository.*;
import org.codewhiskers.vetapp.service.IBloodTypeService;
import org.codewhiskers.vetapp.service.IPatientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PatientServiceImpl implements IPatientService {

    private final PatientRepository patientRepository;
    private final BloodTypeRepository bloodTypeRepository;
    private final BreedRepository breedRepository;
    private final OwnerRepository ownerRepository;
    private final SpeciesRepository speciesRepository;
    private final PatientMapper patientMapper;

    private Patient findPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST, "Patient ID: " + id))
                );
    }


    private BloodType findBloodTypeById(Long id) {
        return bloodTypeRepository.findById(id)
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST, "BloodType ID: " + id))
                );    }

    private Breed findBreedTypeById(Long id) {
        return breedRepository.findById(id)
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST, "Breed ID: " + id))
                );
    }

    private Owner findOwnerById(Long id) {
        return ownerRepository.findById(id)
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST, "Owner ID: " + id))
                );
    }

    private Species findSpeciesById(Long id) {
        return speciesRepository.findById(id)
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST, "Species ID: " + id))
                );
    }

    @Transactional
    @Override
    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        BloodType bloodType = findBloodTypeById(patientRequestDTO.getBloodTypeId());
        Breed breed = findBreedTypeById(patientRequestDTO.getBreedId());
        Owner owner = findOwnerById(patientRequestDTO.getOwnerId());
        Species species = findSpeciesById(patientRequestDTO.getSpeciesId());
        Patient patient = patientMapper.toPatientEntity(patientRequestDTO, owner, species, breed, bloodType);
        patient = patientRepository.save(patient);
        return patientMapper.toPatientResponseDto(patient);
    }

    @Override
    public PatientResponseDTO getPatientById(Long id) {
        Patient patient = findPatientById(id);
        return patientMapper.toPatientResponseDto(patient);
    }

    @Override
    public Page<PatientResponseDTO> getAllPatients(Pageable pageable) {
        Page<Patient> page = patientRepository.findAll(pageable);
        if (page.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.RECORDS_NOT_FOUND, "No patients Found"));
        }
        return page.map(patientMapper::toPatientResponseDto);
    }

    @Transactional
    @Override
    public PatientResponseDTO updatePatient(Long id, PatientRequestDTO patientRequestDTO) {
        BloodType bloodType = findBloodTypeById(patientRequestDTO.getBloodTypeId());
        Breed breed = findBreedTypeById(patientRequestDTO.getBreedId());
        Owner owner = findOwnerById(patientRequestDTO.getOwnerId());
        Species species = findSpeciesById(patientRequestDTO.getSpeciesId());
        Patient patient = findPatientById(id);
        patientMapper.updatePatientEntity(patientRequestDTO, patient, owner, species, breed, bloodType);
        patient = patientRepository.save(patient);
        return patientMapper.toPatientResponseDto(patient);
    }

    @Transactional
    @Override
    public void deletePatient(Long id) {
        Patient patient = findPatientById(id);
        patientRepository.delete(patient);
    }
}
