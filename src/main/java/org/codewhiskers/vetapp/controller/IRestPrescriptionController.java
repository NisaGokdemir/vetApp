package org.codewhiskers.vetapp.controller;

import org.codewhiskers.vetapp.dto.Prescription.request.PrescriptionRequestDTO;
import org.codewhiskers.vetapp.dto.Prescription.response.PrescriptionResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface IRestPrescriptionController {

    ResponseEntity<Page<PrescriptionResponseDTO>> getAllPrescriptions(Pageable pageable);

    ResponseEntity<PrescriptionResponseDTO> getPrescriptionById(Long id);

    ResponseEntity<PrescriptionResponseDTO> createPrescription(PrescriptionRequestDTO prescriptionRequestDTO);

    ResponseEntity<PrescriptionResponseDTO> updatePrescription(Long id, PrescriptionRequestDTO prescriptionRequestDTO);

    void deletePrescription(Long id);

    ResponseEntity<Page<PrescriptionResponseDTO>> getPrescriptionsByDiagnosisId(Long diagnosisId, Pageable pageable);
}
