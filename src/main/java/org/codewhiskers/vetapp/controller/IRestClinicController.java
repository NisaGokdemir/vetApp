package org.codewhiskers.vetapp.controller;

import org.codewhiskers.vetapp.dto.Clinic.request.ClinicRequestDTO;
import org.codewhiskers.vetapp.dto.Clinic.response.ClinicResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface IRestClinicController {

    ResponseEntity<Page<ClinicResponseDTO>> getAllClinics(int page, int size);

    ResponseEntity<ClinicResponseDTO> getClinicById(Long id);

    ResponseEntity<ClinicResponseDTO> createClinic(ClinicRequestDTO clinicRequestDTO);

    ResponseEntity<ClinicResponseDTO> updateClinic(Long id, ClinicRequestDTO clinicRequestDTO);

    ResponseEntity<Void> deleteClinic(Long id);
}
