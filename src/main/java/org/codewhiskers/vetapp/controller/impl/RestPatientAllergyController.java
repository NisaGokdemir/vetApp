package org.codewhiskers.vetapp.controller.impl;

import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.controller.IRestPatientAllergyController;
import org.codewhiskers.vetapp.dto.PatientAllergy.request.PatientAllergyRequestDTO;
import org.codewhiskers.vetapp.dto.PatientAllergy.response.PatientAllergyResponseDTO;
import org.codewhiskers.vetapp.service.IPatientAllergyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patient-allergies")
@RequiredArgsConstructor
public class RestPatientAllergyController implements IRestPatientAllergyController {

    private final IPatientAllergyService patientAllergyService;

    @Override
    public ResponseEntity<PatientAllergyResponseDTO> addPatientAllergy(PatientAllergyRequestDTO patientAllergyRequestDTO) {
        PatientAllergyResponseDTO createdAllergy = patientAllergyService.addPatientAllergy(patientAllergyRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAllergy);
    }

    @Override
    public ResponseEntity<PatientAllergyResponseDTO> updatePatientAllergy(Long id, PatientAllergyRequestDTO patientAllergyRequestDTO) {
        PatientAllergyResponseDTO updatedAllergy = patientAllergyService.updatePatientAllergy(id, patientAllergyRequestDTO);
        return ResponseEntity.ok(updatedAllergy);
    }

    @Override
    public ResponseEntity<PatientAllergyResponseDTO> getPatientAllergyById(Long id) {
        PatientAllergyResponseDTO allergy = patientAllergyService.getPatientAllergyById(id);
        return ResponseEntity.ok(allergy);
    }

    @Override
    public ResponseEntity<List<PatientAllergyResponseDTO>> getAllPatientAllergies() {
        List<PatientAllergyResponseDTO> allergies = patientAllergyService.getAllPatientAllergies();
        return ResponseEntity.ok(allergies);
    }

    @Override
    public ResponseEntity<Void> deletePatientAllergy(Long id) {
        patientAllergyService.deletePatientAllergy(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<PatientAllergyResponseDTO>> getPatientAllergiesByPatientId(Long patientId) {
        List<PatientAllergyResponseDTO> allergies = patientAllergyService.getPatientAllergiesByPatientId(patientId);
        return ResponseEntity.ok(allergies);
    }

    @Override
    public ResponseEntity<List<PatientAllergyResponseDTO>> getPatientAllergiesByAllergy(String allergy) {
        List<PatientAllergyResponseDTO> allergies = patientAllergyService.getPatientAllergiesByAllergy(allergy);
        return ResponseEntity.ok(allergies);
    }

    @Override
    public ResponseEntity<List<PatientAllergyResponseDTO>> getPatientAllergiesBySeverity(String severity) {
        List<PatientAllergyResponseDTO> allergies = patientAllergyService.getPatientAllergiesBySeverity(severity);
        return ResponseEntity.ok(allergies);
    }
} 