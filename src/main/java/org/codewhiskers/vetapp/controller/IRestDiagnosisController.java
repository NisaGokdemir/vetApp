package org.codewhiskers.vetapp.controller;

import org.codewhiskers.vetapp.dto.Diagnosis.request.DiagnosisRequestDTO;
import org.codewhiskers.vetapp.dto.Diagnosis.response.DiagnosisResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface IRestDiagnosisController {
    ResponseEntity<Page<DiagnosisResponseDTO>> getAllDiagnoses(Pageable pageable);

    ResponseEntity<DiagnosisResponseDTO> getDiagnosisById(Long id);

    ResponseEntity<DiagnosisResponseDTO> createDiagnosis(DiagnosisRequestDTO diagnosisRequestDTO);

    ResponseEntity<DiagnosisResponseDTO> updateDiagnosis(Long id, DiagnosisRequestDTO diagnosisRequestDTO);

    void deleteDiagnosis(Long id);
}
