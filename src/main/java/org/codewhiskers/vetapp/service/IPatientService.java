package org.codewhiskers.vetapp.service;

import org.codewhiskers.vetapp.dto.Patient.request.PatientRequestDTO;
import org.codewhiskers.vetapp.dto.Patient.response.PatientResponseDTO;
import org.codewhiskers.vetapp.dto.bloodType.request.BloodTypeRequestDTO;
import org.codewhiskers.vetapp.dto.bloodType.response.BloodTypeResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPatientService {
    PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO);

    PatientResponseDTO getPatientById(Long id);

    Page<PatientResponseDTO> getAllPatients(Pageable pageable);

    PatientResponseDTO updatePatient(Long id, PatientRequestDTO patientRequestDTO);

    void deletePatient(Long id);
}
