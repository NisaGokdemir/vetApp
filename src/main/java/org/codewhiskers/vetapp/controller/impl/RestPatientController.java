package org.codewhiskers.vetapp.controller.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.controller.IRestPatientController;
import org.codewhiskers.vetapp.dto.Patient.request.PatientRequestDTO;
import org.codewhiskers.vetapp.dto.Patient.response.PatientResponseDTO;
import org.codewhiskers.vetapp.service.IPatientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/patients")
public class RestPatientController implements IRestPatientController {

    private final IPatientService patientService;

    @PostMapping
    @Override
    public ResponseEntity<PatientResponseDTO> createPatient(@RequestBody @Valid PatientRequestDTO patientRequestDTO) {
        PatientResponseDTO response = patientService.createPatient(patientRequestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<PatientResponseDTO> getPatientById(@PathVariable Long id) {
        PatientResponseDTO response = patientService.getPatientById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Override
    public ResponseEntity<Page<PatientResponseDTO>> getAllPatients(Pageable pageable) {
        Page<PatientResponseDTO> page = patientService.getAllPatients(pageable);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable Long id,
                                                            @RequestBody @Valid PatientRequestDTO patientRequestDTO) {
        PatientResponseDTO response = patientService.updatePatient(id, patientRequestDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}
