package org.codewhiskers.vetapp.service;

import org.codewhiskers.vetapp.dto.Clinic.request.ClinicRequestDTO;
import org.codewhiskers.vetapp.dto.Clinic.response.ClinicResponseDTO;
import org.springframework.data.domain.Page;

public interface IClinicService {

    Page<ClinicResponseDTO> getAllClinics(int page, int size);

    ClinicResponseDTO getClinicById(Long id);

    ClinicResponseDTO createClinic(ClinicRequestDTO clinicRequestDTO);

    ClinicResponseDTO updateClinic(Long id, ClinicRequestDTO clinicRequestDTO);

    void deleteClinic(Long id);
}
