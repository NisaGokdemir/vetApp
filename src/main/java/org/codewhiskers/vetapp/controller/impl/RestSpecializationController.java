package org.codewhiskers.vetapp.controller.impl;

import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.controller.IRestSpecializationController;
import org.codewhiskers.vetapp.dto.Specialization.request.SpecializationRequestDTO;
import org.codewhiskers.vetapp.dto.Specialization.response.SpecializationResponseDTO;
import org.codewhiskers.vetapp.service.ISpecializationService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/specializations")
public class RestSpecializationController implements IRestSpecializationController {

    private final ISpecializationService specializationService;

    @GetMapping("/all")
    @Override
    public ResponseEntity<Page<SpecializationResponseDTO>> getAllSpecializations( @RequestParam(defaultValue = "0") int page,
                                                                                  @RequestParam(defaultValue = "10") int size) {
        Page<SpecializationResponseDTO> specializations = specializationService.getAllSpecializations(page, size);
        if (specializations.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(specializations);
    }

    @PostMapping("/create")
    @Override
    public ResponseEntity<SpecializationResponseDTO> createSpecialization(@RequestBody SpecializationRequestDTO specializationRequestDTO) {
        SpecializationResponseDTO specializationResponseDTO = specializationService.createSpecialization(specializationRequestDTO);
        if (specializationResponseDTO == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(specializationResponseDTO);
    }

    @PutMapping("/update/{id}")
    @Override
    public ResponseEntity<SpecializationResponseDTO> updateSpecialization(@PathVariable(name = "id") Long id, @RequestBody SpecializationRequestDTO specializationRequestDTO) {
        SpecializationResponseDTO specializationResponseDTO = specializationService.updateSpecialization(id, specializationRequestDTO);
        if (specializationResponseDTO == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(specializationResponseDTO);
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public void deleteSpecialization(@PathVariable(name = "id") Long id) {
        specializationService.deleteSpecialization(id);
        ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<SpecializationResponseDTO> getSpecializationById(@PathVariable(name = "id") Long id) {
        SpecializationResponseDTO specializationResponseDTO = specializationService.getSpecializationById(id);
        if (specializationResponseDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(specializationResponseDTO);
    }
}
