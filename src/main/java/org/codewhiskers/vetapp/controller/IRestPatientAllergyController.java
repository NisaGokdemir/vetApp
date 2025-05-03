package org.codewhiskers.vetapp.controller;

import org.codewhiskers.vetapp.dto.PatientAllergy.request.PatientAllergyRequestDTO;
import org.codewhiskers.vetapp.dto.PatientAllergy.response.PatientAllergyResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IRestPatientAllergyController {

    /**
     * Yeni bir hasta alerjisi ekler
     * @param patientAllergyRequestDTO hasta alerjisi bilgileri
     * @return eklenen hasta alerjisi
     */
    @PostMapping
    ResponseEntity<PatientAllergyResponseDTO> addPatientAllergy(@RequestBody PatientAllergyRequestDTO patientAllergyRequestDTO);

    /**
     * Var olan bir hasta alerjisini günceller
     * @param id güncellenecek alerji ID'si
     * @param patientAllergyRequestDTO güncellenmiş hasta alerjisi bilgileri
     * @return güncellenen hasta alerjisi
     */
    @PutMapping("/{id}")
    ResponseEntity<PatientAllergyResponseDTO> updatePatientAllergy(
            @PathVariable Long id, 
            @RequestBody PatientAllergyRequestDTO patientAllergyRequestDTO);

    /**
     * ID'ye göre hasta alerjisini getirir
     * @param id alerji ID'si
     * @return bulunan hasta alerjisi
     */
    @GetMapping("/{id}")
    ResponseEntity<PatientAllergyResponseDTO> getPatientAllergyById(@PathVariable Long id);

    /**
     * Tüm hasta alerjilerini listeler
     * @return hasta alerjileri listesi
     */
    @GetMapping
    ResponseEntity<List<PatientAllergyResponseDTO>> getAllPatientAllergies();

    /**
     * Hasta alerjisini siler
     * @param id silinecek alerji ID'si
     * @return işlem durumu
     */
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletePatientAllergy(@PathVariable Long id);

    /**
     * Belirli bir hastanın alerjilerini getirir
     * @param patientId hasta ID'si
     * @return hastanın alerjileri
     */
    @GetMapping("/patient/{patientId}")
    ResponseEntity<List<PatientAllergyResponseDTO>> getPatientAllergiesByPatientId(@PathVariable Long patientId);

    /**
     * Belirli bir alerji adına göre hasta alerjilerini getirir
     * @param allergy alerji adı
     * @return hasta alerjileri listesi
     */
    @GetMapping("/search/allergy")
    ResponseEntity<List<PatientAllergyResponseDTO>> getPatientAllergiesByAllergy(@RequestParam String allergy);

    /**
     * Belirli bir şiddet seviyesine göre hasta alerjilerini getirir
     * @param severity şiddet seviyesi
     * @return hasta alerjileri listesi
     */
    @GetMapping("/search/severity")
    ResponseEntity<List<PatientAllergyResponseDTO>> getPatientAllergiesBySeverity(@RequestParam String severity);
} 