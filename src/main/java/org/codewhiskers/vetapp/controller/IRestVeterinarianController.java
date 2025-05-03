package org.codewhiskers.vetapp.controller;

import org.codewhiskers.vetapp.dto.Veterinarian.request.VeterinarianRequestDTO;
import org.codewhiskers.vetapp.dto.Veterinarian.response.VeterinarianResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface IRestVeterinarianController {

    ResponseEntity<VeterinarianResponseDTO> getVeterinarianById(Long id);

    ResponseEntity<VeterinarianResponseDTO> createVeterinarian(VeterinarianRequestDTO veterinarianRequestDTO);

    ResponseEntity<VeterinarianResponseDTO> updateVeterinarian(Long id, VeterinarianRequestDTO veterinarianRequestDTO);

    void deleteVeterinarian(Long id);

    ResponseEntity<Page<VeterinarianResponseDTO>> getAllVeterinarians(int page, int size);
}
