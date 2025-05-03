package org.codewhiskers.vetapp.controller.impl;

import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.controller.IRestVeterinarianController;
import org.codewhiskers.vetapp.dto.Veterinarian.request.VeterinarianRequestDTO;
import org.codewhiskers.vetapp.dto.Veterinarian.response.VeterinarianResponseDTO;
import org.codewhiskers.vetapp.service.IVeterinarianService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/veterinarians")
public class RestVeterinarianController implements IRestVeterinarianController {

    private final IVeterinarianService veterinarianService;

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<VeterinarianResponseDTO> getVeterinarianById(@PathVariable Long id) {
        VeterinarianResponseDTO veterinarianResponseDTO = veterinarianService.getVeterinarianById(id);
        if (veterinarianResponseDTO != null) {
            return ResponseEntity.ok(veterinarianResponseDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    @Override
    public ResponseEntity<VeterinarianResponseDTO> createVeterinarian(@RequestBody VeterinarianRequestDTO veterinarianRequestDTO) {
        VeterinarianResponseDTO veterinarianResponseDTO = veterinarianService.createVeterinarian(veterinarianRequestDTO);
        if (veterinarianResponseDTO != null) {
            return ResponseEntity.ok(veterinarianResponseDTO);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<VeterinarianResponseDTO> updateVeterinarian(@PathVariable Long id, @RequestBody VeterinarianRequestDTO veterinarianRequestDTO) {
        VeterinarianResponseDTO veterinarianResponseDTO = veterinarianService.updateVeterinarian(id, veterinarianRequestDTO);
        if (veterinarianResponseDTO != null) {
            return ResponseEntity.ok(veterinarianResponseDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Override
    public void deleteVeterinarian(@PathVariable Long id) {
        veterinarianService.deleteVeterinarian(id);
        ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    @Override
    public ResponseEntity<Page<VeterinarianResponseDTO>> getAllVeterinarians(@RequestParam(defaultValue = "0") int page,
                                                                             @RequestParam(defaultValue = "10") int size) {
        Page<VeterinarianResponseDTO> veterinarians = veterinarianService.getAllVeterinarians(page, size);
        if (veterinarians != null) {
            return ResponseEntity.ok(veterinarians);
        }
        return ResponseEntity.notFound().build();
    }
}
