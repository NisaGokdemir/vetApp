package org.codewhiskers.vetapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.dto.Diagnosis.request.DiagnosisRequestDTO;
import org.codewhiskers.vetapp.dto.Diagnosis.response.DiagnosisResponseDTO;
import org.codewhiskers.vetapp.entity.Clinic;
import org.codewhiskers.vetapp.entity.Diagnosis;
import org.codewhiskers.vetapp.entity.Disease;
import org.codewhiskers.vetapp.entity.Patient;
import org.codewhiskers.vetapp.entity.Veterinarian;
import org.codewhiskers.vetapp.exception.BaseException;
import org.codewhiskers.vetapp.exception.ErrorMessage;
import org.codewhiskers.vetapp.exception.MessageType;
import org.codewhiskers.vetapp.mapper.DiagnosisMapper;
import org.codewhiskers.vetapp.repository.ClinicRepository;
import org.codewhiskers.vetapp.repository.DiagnosisRepository;
import org.codewhiskers.vetapp.repository.DiseaseRepository;
import org.codewhiskers.vetapp.repository.PatientRepository;
import org.codewhiskers.vetapp.repository.VeterinarianRepository;
import org.codewhiskers.vetapp.service.IDiagnosisService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiagnosisServiceImpl implements IDiagnosisService {

    private final DiagnosisRepository diagnosisRepository;
    private final DiagnosisMapper diagnosisMapper;
    private final VeterinarianRepository veterinarianRepository;
    private final PatientRepository patientRepository;
    private final ClinicRepository clinicRepository;
    private final DiseaseRepository diseaseRepository;

    private Diagnosis findDiagnosisById(Long id) {
        return diagnosisRepository.findById(id).orElseThrow(
                () -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,id.toString()))
        );
    }

    private Veterinarian findVeterinarianById(Long id) {
        return veterinarianRepository.findById(id).orElseThrow(
                () -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,"Veteriner bulunamadı: " + id))
        );
    }

    private Patient findPatientById(Long id) {
        return patientRepository.findById(id).orElseThrow(
                () -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,"Hasta bulunamadı: " + id))
        );
    }

    private Clinic findClinicById(Long id) {
        return clinicRepository.findById(id).orElseThrow(
                () -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,"Klinik bulunamadı: " + id))
        );
    }
    
    private Disease findDiseaseById(Long id) {
        return diseaseRepository.findById(id).orElseThrow(
                () -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,"Hastalık bulunamadı: " + id))
        );
    }

    @Override
    public Page<DiagnosisResponseDTO> getAllDiagnoses(Pageable pageable) {
        Page<Diagnosis> diagnoses = diagnosisRepository.findAll(pageable);
        if(!diagnoses.hasContent()) {
            throw new BaseException(new ErrorMessage(MessageType.RECORDS_NOT_FOUND,""));
        }
        return diagnoses.map(diagnosisMapper::diagnosisToResponseDTO);
    }

    @Override
    public DiagnosisResponseDTO getDiagnosisById(Long id) {
        Diagnosis diagnosis = findDiagnosisById(id);
        return diagnosisMapper.diagnosisToResponseDTO(diagnosis);
    }

    @Override
    public DiagnosisResponseDTO createDiagnosis(DiagnosisRequestDTO diagnosisRequestDTO) {
        Diagnosis diagnosis = diagnosisMapper.requestDTOToDiagnosis(diagnosisRequestDTO);
        if(diagnosis.getDiagnosis() == null || diagnosis.getDiagnosis().isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.RECORD_CREATE_UNSUCCESS,""));
        }
        
        // İlişkili nesneleri bul
        Veterinarian veterinarian = findVeterinarianById(diagnosisRequestDTO.getVeterinarianId());
        Patient patient = findPatientById(diagnosisRequestDTO.getPatientId());
        Clinic clinic = findClinicById(diagnosisRequestDTO.getClinicId());
        Disease disease = findDiseaseById(diagnosisRequestDTO.getDiseaseId());
        
        // İlişkileri ayarla
        diagnosis.setClinic(clinic);
        diagnosis.setVeterinarian(veterinarian);
        diagnosis.setPatient(patient);
        diagnosis.setDisease(disease);
        
        // Kaydet
        diagnosis = diagnosisRepository.save(diagnosis);
        return diagnosisMapper.diagnosisToResponseDTO(diagnosis);
    }

    @Override
    public DiagnosisResponseDTO updateDiagnosis(Long id, DiagnosisRequestDTO diagnosisRequestDTO) {
        Diagnosis diagnosis = findDiagnosisById(id);
        
        // İlişkili nesneleri bul
        Veterinarian veterinarian = findVeterinarianById(diagnosisRequestDTO.getVeterinarianId());
        Patient patient = findPatientById(diagnosisRequestDTO.getPatientId());
        Clinic clinic = findClinicById(diagnosisRequestDTO.getClinicId());
        Disease disease = findDiseaseById(diagnosisRequestDTO.getDiseaseId());
        
        // DTO'dan bilgileri entity'ye güncelle
        diagnosisMapper.updateDiagnosisFromRequestDTO(diagnosisRequestDTO, diagnosis);
        
        // İlişkileri güncelle
        diagnosis.setVeterinarian(veterinarian);
        diagnosis.setPatient(patient);
        diagnosis.setClinic(clinic);
        diagnosis.setDisease(disease);
        
        if (diagnosis.getDiagnosis() == null || diagnosis.getDiagnosis().isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.RECORD_UPDATE_UNSUCCESS,id.toString()));
        }
        
        // Kaydet
        diagnosis = diagnosisRepository.save(diagnosis);
        return diagnosisMapper.diagnosisToResponseDTO(diagnosis);
    }

    @Override
    public void deleteDiagnosis(Long id) {
        Diagnosis diagnosis = findDiagnosisById(id);
        diagnosisRepository.delete(diagnosis);
        if (diagnosisRepository.existsById(id)) {
            throw new BaseException(new ErrorMessage(MessageType.RECORD_DELETE_UNSUCCESS,id.toString()));
        }
    }

    @Override
    public List<DiagnosisResponseDTO> getDiagnosesByPatientId(Long patientId) {
        // Patient'ın varlığını kontrol etmek iyi bir pratik olabilir
        findPatientById(patientId); // Patient yoksa exception fırlatır

        // DiagnosisRepository'de findByPatientId metodu olmalı
        List<Diagnosis> diagnoses = diagnosisRepository.findByPatientId(patientId);

        // Boş liste kontrolü (isteğe bağlı, frontend de yapabilir)
        // if (diagnoses.isEmpty()) {
        //     throw new BaseException(new ErrorMessage(MessageType.RECORDS_NOT_FOUND, "Bu hastaya ait tanı bulunamadı"));
        // }

        return diagnoses.stream()
                .map(diagnosisMapper::diagnosisToResponseDTO)
                .collect(Collectors.toList());
    }
}
