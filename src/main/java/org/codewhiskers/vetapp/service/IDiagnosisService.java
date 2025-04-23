package org.codewhiskers.vetapp.service;

import org.codewhiskers.vetapp.dto.Diagnosis.request.DiagnosisRequestDTO;
import org.codewhiskers.vetapp.dto.Diagnosis.response.DiagnosisResponseDTO;
import org.codewhiskers.vetapp.entity.Diagnosis;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IDiagnosisService {

    Page<DiagnosisResponseDTO> getAllDiagnoses(Pageable pageable);

    DiagnosisResponseDTO getDiagnosisById(Long id);

    DiagnosisResponseDTO createDiagnosis(DiagnosisRequestDTO diagnosisRequestDTO);

    DiagnosisResponseDTO updateDiagnosis(Long id, DiagnosisRequestDTO diagnosisRequestDTO);

    void deleteDiagnosis(Long id);

}
