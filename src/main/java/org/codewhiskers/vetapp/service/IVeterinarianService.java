package org.codewhiskers.vetapp.service;

import org.codewhiskers.vetapp.dto.Veterinarian.request.VeterinarianRequestDTO;
import org.codewhiskers.vetapp.dto.Veterinarian.response.VeterinarianResponseDTO;
import org.springframework.data.domain.Page;

public interface IVeterinarianService {

    VeterinarianResponseDTO getVeterinarianById(Long id);

    VeterinarianResponseDTO createVeterinarian(VeterinarianRequestDTO veterinarianRequestDTO);

    VeterinarianResponseDTO updateVeterinarian(Long id, VeterinarianRequestDTO veterinarianRequestDTO);

    void deleteVeterinarian(Long id);

    Page<VeterinarianResponseDTO> getAllVeterinarians(int page, int size);
}
