package org.codewhiskers.vetapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.dto.PatientAllergy.request.PatientAllergyRequestDTO;
import org.codewhiskers.vetapp.dto.PatientAllergy.response.PatientAllergyResponseDTO;
import org.codewhiskers.vetapp.entity.Patient;
import org.codewhiskers.vetapp.entity.PatientAllergy;
import org.codewhiskers.vetapp.exception.BaseException;
import org.codewhiskers.vetapp.exception.ErrorMessage;
import org.codewhiskers.vetapp.exception.MessageType;
import org.codewhiskers.vetapp.mapper.PatientAllergyMapper;
import org.codewhiskers.vetapp.repository.PatientAllergyRepository;
import org.codewhiskers.vetapp.repository.PatientRepository;
import org.codewhiskers.vetapp.service.IPatientAllergyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientAllergyServiceImpl implements IPatientAllergyService {

    private final PatientAllergyRepository patientAllergyRepository;
    private final PatientRepository patientRepository;
    private final PatientAllergyMapper patientAllergyMapper;
    
    /**
     * ID'ye göre hasta alerjisi bulur
     * @param id alerji ID'si
     * @return bulunan hasta alerjisi
     */
    private PatientAllergy findPatientAllergyById(Long id) {
        return patientAllergyRepository.findById(id)
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "Hasta alerjisi bulunamadı: " + id)));
    }
    
    /**
     * ID'ye göre hastayı bulur
     * @param id hasta ID'si
     * @return bulunan hasta
     */
    private Patient findPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "Hasta bulunamadı: " + id)));
    }

    @Override
    @Transactional
    public PatientAllergyResponseDTO addPatientAllergy(PatientAllergyRequestDTO patientAllergyRequestDTO) {
        // DTO'dan entity oluştur
        PatientAllergy patientAllergy = patientAllergyMapper.requestDTOToPatientAllergy(patientAllergyRequestDTO);
        
        // Hastayı kontrol et ve ata
        Patient patient = findPatientById(patientAllergyRequestDTO.getPatientId());
        patientAllergy.setPatient(patient);
        
        // Alerji bilgilerini kontrol et
        if (patientAllergy.getAllergy() == null || patientAllergy.getAllergy().isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.RECORD_CREATE_UNSUCCESS, "Alerji adı boş olamaz"));
        }
        
        // Kaydet
        PatientAllergy savedAllergy = patientAllergyRepository.save(patientAllergy);
        
        // Response DTO'ya dönüştür ve döndür
        return patientAllergyMapper.patientAllergyToResponseDTO(savedAllergy);
    }

    @Override
    @Transactional
    public PatientAllergyResponseDTO updatePatientAllergy(Long id, PatientAllergyRequestDTO patientAllergyRequestDTO) {
        // Hasta alerjisini bul
        PatientAllergy patientAllergy = findPatientAllergyById(id);
        
        // Hastayı kontrol et ve ata
        Patient patient = findPatientById(patientAllergyRequestDTO.getPatientId());
        
        // Entity'yi güncelle
        patientAllergyMapper.updatePatientAllergyFromRequestDTO(patientAllergyRequestDTO, patientAllergy);
        patientAllergy.setPatient(patient);
        
        // Alerji bilgilerini kontrol et
        if (patientAllergy.getAllergy() == null || patientAllergy.getAllergy().isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.RECORD_UPDATE_UNSUCCESS, "Alerji adı boş olamaz"));
        }
        
        // Kaydet
        PatientAllergy updatedAllergy = patientAllergyRepository.save(patientAllergy);
        
        // Response DTO'ya dönüştür ve döndür
        return patientAllergyMapper.patientAllergyToResponseDTO(updatedAllergy);
    }

    @Override
    public PatientAllergyResponseDTO getPatientAllergyById(Long id) {
        PatientAllergy patientAllergy = findPatientAllergyById(id);
        return patientAllergyMapper.patientAllergyToResponseDTO(patientAllergy);
    }

    @Override
    public List<PatientAllergyResponseDTO> getAllPatientAllergies() {
        List<PatientAllergy> patientAllergies = patientAllergyRepository.findAll();
        return patientAllergies.stream()
                .map(patientAllergyMapper::patientAllergyToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deletePatientAllergy(Long id) {
        PatientAllergy patientAllergy = findPatientAllergyById(id);
        patientAllergyRepository.delete(patientAllergy);
    }

    @Override
    public List<PatientAllergyResponseDTO> getPatientAllergiesByPatientId(Long patientId) {
        // Hastayı kontrol et
        findPatientById(patientId);
        
        // Hastanın alerjilerini bul
        List<PatientAllergy> patientAllergies = patientAllergyRepository.findByPatientId(patientId);
        
        // Response DTO'lara dönüştür ve döndür
        return patientAllergies.stream()
                .map(patientAllergyMapper::patientAllergyToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PatientAllergyResponseDTO> getPatientAllergiesByAllergy(String allergy) {
        List<PatientAllergy> patientAllergies = patientAllergyRepository.findByAllergyContainingIgnoreCase(allergy);
        return patientAllergies.stream()
                .map(patientAllergyMapper::patientAllergyToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PatientAllergyResponseDTO> getPatientAllergiesBySeverity(String severity) {
        List<PatientAllergy> patientAllergies = patientAllergyRepository.findBySeverityContainingIgnoreCase(severity);
        return patientAllergies.stream()
                .map(patientAllergyMapper::patientAllergyToResponseDTO)
                .collect(Collectors.toList());
    }
} 