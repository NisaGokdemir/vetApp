package org.codewhiskers.vetapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.dto.Diagnosis.request.DiagnosisRequestDTO;
import org.codewhiskers.vetapp.dto.Diagnosis.response.DiagnosisResponseDTO;
import org.codewhiskers.vetapp.entity.Diagnosis;
import org.codewhiskers.vetapp.entity.Patient;
import org.codewhiskers.vetapp.entity.User;
import org.codewhiskers.vetapp.exception.BaseException;
import org.codewhiskers.vetapp.exception.ErrorMessage;
import org.codewhiskers.vetapp.exception.MessageType;
import org.codewhiskers.vetapp.mapper.DiagnosisMapper;
import org.codewhiskers.vetapp.repository.DiagnosisRepository;
import org.codewhiskers.vetapp.repository.PatientRepository;
import org.codewhiskers.vetapp.repository.UserRepository;
import org.codewhiskers.vetapp.service.IDiagnosisService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiagnosisServiceImpl implements IDiagnosisService {

    private final DiagnosisRepository diagnosisRepository;
    private final DiagnosisMapper diagnosisMapper;
    private final UserRepository userRepository;
    private final PatientRepository patientRepository;

    private Diagnosis findDiagnosisById(Long id) {
        return diagnosisRepository.findById(id).orElseThrow(
                () -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,id.toString()))
        );
    }

    private User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,id.toString()))
        );
    }

    private Patient findPatientById(Long id) {
        return patientRepository.findById(id).orElseThrow(
                () -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,id.toString()))
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
        User vet = findUserById(diagnosisRequestDTO.getVetId());
        Patient patient = findPatientById(diagnosisRequestDTO.getPatientId());
        diagnosis.setVet(vet);
        diagnosis.setPatient(patient);
        diagnosis = diagnosisRepository.save(diagnosis);
        return diagnosisMapper.diagnosisToResponseDTO(diagnosis);
    }

    @Override
    public DiagnosisResponseDTO updateDiagnosis(Long id, DiagnosisRequestDTO diagnosisRequestDTO) {
        Diagnosis diagnosis = findDiagnosisById(id);
        User vet = findUserById(diagnosisRequestDTO.getVetId());
        Patient patient = findPatientById(diagnosisRequestDTO.getPatientId());
        diagnosisMapper.updateDiagnosisFromRequestDTO(diagnosisRequestDTO, diagnosis);
        diagnosis.setVet(vet);
        diagnosis.setPatient(patient);
        if (diagnosis.getDiagnosis() == null || diagnosis.getDiagnosis().isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.RECORD_UPDATE_UNSUCCESS,id.toString()));
        }
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
}
