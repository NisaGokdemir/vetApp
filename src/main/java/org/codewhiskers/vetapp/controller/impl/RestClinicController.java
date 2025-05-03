package org.codewhiskers.vetapp.controller.impl;

import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.controller.IRestClinicController;
import org.codewhiskers.vetapp.dto.Clinic.request.ClinicRequestDTO;
import org.codewhiskers.vetapp.dto.Clinic.response.ClinicResponseDTO;
import org.codewhiskers.vetapp.service.IClinicService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clinics")
public class RestClinicController implements IRestClinicController {

    private final IClinicService clinicService;

    @GetMapping("/all")
    @Override
    public ResponseEntity<Page<ClinicResponseDTO>> getAllClinics(@RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "10") int size) {
        Page<ClinicResponseDTO> clinics = clinicService.getAllClinics(page, size);
        if (clinics.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clinics);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<ClinicResponseDTO> getClinicById(@PathVariable Long id) {
        ClinicResponseDTO clinicResponseDTO = clinicService.getClinicById(id);
        if (clinicResponseDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clinicResponseDTO);
    }

    @PostMapping("/create")
    @Override
    public ResponseEntity<ClinicResponseDTO> createClinic(@RequestBody ClinicRequestDTO clinicRequestDTO) {
        ClinicResponseDTO clinicResponseDTO = clinicService.createClinic(clinicRequestDTO);
        if (clinicResponseDTO == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(clinicResponseDTO);
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<ClinicResponseDTO> updateClinic(@PathVariable Long id, @RequestBody ClinicRequestDTO clinicRequestDTO) {
        ClinicResponseDTO clinicResponseDTO = clinicService.updateClinic(id, clinicRequestDTO);
        if (clinicResponseDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clinicResponseDTO);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> deleteClinic(@PathVariable Long id) {
        clinicService.deleteClinic(id); // Silme işlemi
        return ResponseEntity.ok().build(); // Başarılı yanıt
    }
}
