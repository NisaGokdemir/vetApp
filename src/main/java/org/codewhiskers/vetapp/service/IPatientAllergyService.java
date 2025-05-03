package org.codewhiskers.vetapp.service;

import org.codewhiskers.vetapp.dto.PatientAllergy.request.PatientAllergyRequestDTO;
import org.codewhiskers.vetapp.dto.PatientAllergy.response.PatientAllergyResponseDTO;

import java.util.List;

public interface IPatientAllergyService {
    
    /**
     * Yeni bir hasta alerjisi ekler
     * @param patientAllergyRequestDTO hasta alerjisi bilgileri
     * @return eklenen hasta alerjisi
     */
    PatientAllergyResponseDTO addPatientAllergy(PatientAllergyRequestDTO patientAllergyRequestDTO);
    
    /**
     * Var olan bir hasta alerjisini günceller
     * @param id güncellenecek alerji ID'si
     * @param patientAllergyRequestDTO güncellenmiş hasta alerjisi bilgileri
     * @return güncellenen hasta alerjisi
     */
    PatientAllergyResponseDTO updatePatientAllergy(Long id, PatientAllergyRequestDTO patientAllergyRequestDTO);
    
    /**
     * ID'ye göre hasta alerjisini getirir
     * @param id alerji ID'si
     * @return bulunan hasta alerjisi
     */
    PatientAllergyResponseDTO getPatientAllergyById(Long id);
    
    /**
     * Tüm hasta alerjilerini listeler
     * @return hasta alerjileri listesi
     */
    List<PatientAllergyResponseDTO> getAllPatientAllergies();
    
    /**
     * Hasta alerjisini siler
     * @param id silinecek alerji ID'si
     */
    void deletePatientAllergy(Long id);
    
    /**
     * Belirli bir hastanın alerjilerini getirir
     * @param patientId hasta ID'si
     * @return hastanın alerjileri
     */
    List<PatientAllergyResponseDTO> getPatientAllergiesByPatientId(Long patientId);
    
    /**
     * Belirli bir alerji adına göre hasta alerjilerini getirir
     * @param allergy alerji adı
     * @return hasta alerjileri listesi
     */
    List<PatientAllergyResponseDTO> getPatientAllergiesByAllergy(String allergy);
    
    /**
     * Belirli bir şiddet seviyesine göre hasta alerjilerini getirir
     * @param severity şiddet seviyesi
     * @return hasta alerjileri listesi
     */
    List<PatientAllergyResponseDTO> getPatientAllergiesBySeverity(String severity);
} 