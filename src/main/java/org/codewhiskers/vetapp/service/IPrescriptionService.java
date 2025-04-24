package org.codewhiskers.vetapp.service;

import org.codewhiskers.vetapp.dto.Prescription.request.PrescriptionRequestDTO;
import org.codewhiskers.vetapp.dto.Prescription.response.PrescriptionResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPrescriptionService {

    Page<PrescriptionResponseDTO> getAllPrescriptions(Pageable pageable);

    PrescriptionResponseDTO getPrescriptionById(Long id);

    PrescriptionResponseDTO createPrescription(PrescriptionRequestDTO prescriptionRequestDTO);

    PrescriptionResponseDTO updatePrescription(Long id, PrescriptionRequestDTO prescriptionRequestDTO);

    void deletePrescription(Long id);

    Page<PrescriptionResponseDTO> getPrescriptionsByDiagnosisId(Long diagnosisId, Pageable pageable);
}
