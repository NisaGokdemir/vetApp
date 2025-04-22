package org.codewhiskers.vetapp.controller;

import jakarta.validation.Valid;
import org.codewhiskers.vetapp.dto.Patient.request.PatientRequestDTO;
import org.codewhiskers.vetapp.dto.Patient.response.PatientResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface IRestPatientController {

    ResponseEntity<PatientResponseDTO> createPatient(@Valid PatientRequestDTO patientRequestDTO);

    ResponseEntity<PatientResponseDTO> getPatientById(Long id);

    ResponseEntity<Page<PatientResponseDTO>> getAllPatients(Pageable pageable);

    ResponseEntity<PatientResponseDTO> updatePatient(Long id, @Valid PatientRequestDTO patientRequestDTO);

    ResponseEntity<Void> deletePatient(Long id);
}
