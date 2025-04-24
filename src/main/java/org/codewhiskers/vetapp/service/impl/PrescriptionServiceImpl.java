package org.codewhiskers.vetapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.dto.Prescription.request.PrescriptionRequestDTO;
import org.codewhiskers.vetapp.dto.Prescription.response.PrescriptionResponseDTO;
import org.codewhiskers.vetapp.entity.Diagnosis;
import org.codewhiskers.vetapp.entity.Prescription;
import org.codewhiskers.vetapp.exception.BaseException;
import org.codewhiskers.vetapp.exception.ErrorMessage;
import org.codewhiskers.vetapp.exception.MessageType;
import org.codewhiskers.vetapp.mapper.PrescriptionMapper;
import org.codewhiskers.vetapp.repository.DiagnosisRepository;
import org.codewhiskers.vetapp.repository.PrescriptionRepository;
import org.codewhiskers.vetapp.service.IPrescriptionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrescriptionServiceImpl implements IPrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final PrescriptionMapper prescriptionMapper;
    private final DiagnosisRepository diagnosisRepository;

    private Prescription findPrescriptionById(Long id) {
        return prescriptionRepository.findById(id)
                .orElseThrow(
                        () -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,id.toString()))
                );
    }

    private Diagnosis findDiagnosisById(Long id) {
        return diagnosisRepository.findById(id)
                .orElseThrow(
                        () -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,id.toString()))
                );
    }

    @Override
    public Page<PrescriptionResponseDTO> getAllPrescriptions(Pageable pageable) {
        Page<Prescription> prescriptions = prescriptionRepository.findAll(pageable);
        if (!prescriptions.hasContent()) {
            throw new BaseException(new ErrorMessage(MessageType.RECORDS_NOT_FOUND, ""));
        }
        return prescriptions.map(prescriptionMapper::prescriptionToResponseDTO);
    }

    @Override
    public PrescriptionResponseDTO getPrescriptionById(Long id) {
        Prescription prescription = findPrescriptionById(id);
        return prescriptionMapper.prescriptionToResponseDTO(prescription);
    }

    @Override
    public PrescriptionResponseDTO createPrescription(PrescriptionRequestDTO prescriptionRequestDTO) {
        Prescription prescription = prescriptionMapper.requestDTOToPrescription(prescriptionRequestDTO);
        if (prescriptionRequestDTO.getDiagnosisId() == null) {
            throw new BaseException(new ErrorMessage(MessageType.REQUIRED_FIELD_MISSING, "diagnosisId"));
        }
        Diagnosis diagnosis = findDiagnosisById(prescriptionRequestDTO.getDiagnosisId());
        prescription.setDiagnosis(diagnosis);
        prescriptionRepository.save(prescription);
        if (prescription.getId() == null) {
            throw new BaseException(new ErrorMessage(MessageType.RECORD_CREATE_UNSUCCESS, prescription.getId().toString()));
        }
        return prescriptionMapper.prescriptionToResponseDTO(prescription);
    }

    @Override
    public PrescriptionResponseDTO updatePrescription(Long id, PrescriptionRequestDTO prescriptionRequestDTO) {
        Prescription prescription = findPrescriptionById(id);
        prescriptionMapper.updatePrescriptionFromRequestDTO(prescriptionRequestDTO, prescription);
        if (prescriptionRequestDTO.getDiagnosisId() == null) {
            throw new BaseException(new ErrorMessage(MessageType.REQUIRED_FIELD_MISSING, "diagnosisId"));
        }
        Diagnosis diagnosis = findDiagnosisById(prescriptionRequestDTO.getDiagnosisId());
        prescription.setDiagnosis(diagnosis);
        prescriptionRepository.save(prescription);
        if (prescription.getId() == null) {
            throw new BaseException(new ErrorMessage(MessageType.RECORD_UPDATE_UNSUCCESS, prescription.getId().toString()));
        }
        return prescriptionMapper.prescriptionToResponseDTO(prescription);
    }

    @Override
    public void deletePrescription(Long id) {
        Prescription prescription = findPrescriptionById(id);
        prescriptionRepository.delete(prescription);
        if (prescriptionRepository.existsById(id)) {
            throw new BaseException(new ErrorMessage(MessageType.RECORD_DELETE_UNSUCCESS, id.toString()));
        }
    }

    @Override
    public Page<PrescriptionResponseDTO> getPrescriptionsByDiagnosisId(Long diagnosisId, Pageable pageable) {
        Diagnosis diagnosis = findDiagnosisById(diagnosisId);
        Page<Prescription> prescriptions = prescriptionRepository.findByDiagnosis(diagnosis, pageable);
        if (!prescriptions.hasContent()) {
            throw new BaseException(new ErrorMessage(MessageType.RECORDS_NOT_FOUND, ""));
        }
        return prescriptions.map(prescriptionMapper::prescriptionToResponseDTO);
    }
}
