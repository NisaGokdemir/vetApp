package org.codewhiskers.vetapp.controller.impl;

import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.controller.IRestDiseaseController;
import org.codewhiskers.vetapp.dto.Disease.request.DiseaseRequestDTO;
import org.codewhiskers.vetapp.dto.Disease.response.DiseaseResponseDTO;
import org.codewhiskers.vetapp.service.IDiseaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/diseases")
@RequiredArgsConstructor
public class RestDiseaseController implements IRestDiseaseController {

    private final IDiseaseService diseaseService;

    /**
     * Yeni hastalık ekler
     * @param diseaseRequestDTO hastalık bilgileri
     * @return eklenen hastalık
     */
    @Override
    public ResponseEntity<DiseaseResponseDTO> addDisease(DiseaseRequestDTO diseaseRequestDTO) {
        DiseaseResponseDTO createdDisease = diseaseService.addDisease(diseaseRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDisease);
    }

    /**
     * Hastalığı günceller
     * @param id güncellenecek hastalık id'si
     * @param diseaseRequestDTO güncellenmiş hastalık bilgileri
     * @return güncellenen hastalık
     */
    @Override
    public ResponseEntity<DiseaseResponseDTO> updateDisease(Long id, DiseaseRequestDTO diseaseRequestDTO) {
        DiseaseResponseDTO updatedDisease = diseaseService.updateDisease(id, diseaseRequestDTO);
        return ResponseEntity.ok(updatedDisease);
    }

    /**
     * ID'ye göre hastalığı getirir
     * @param id hastalık id'si
     * @return bulunan hastalık
     */
    @Override
    public ResponseEntity<DiseaseResponseDTO> getDiseaseById(Long id) {
        DiseaseResponseDTO disease = diseaseService.getDiseaseById(id);
        return ResponseEntity.ok(disease);
    }

    /**
     * Tüm hastalıkları listeler
     * @return hastalık listesi
     */
    @Override
    public ResponseEntity<List<DiseaseResponseDTO>> getAllDiseases() {
        List<DiseaseResponseDTO> diseases = diseaseService.getAllDiseases();
        return ResponseEntity.ok(diseases);
    }

    /**
     * Hastalığı siler
     * @param id silinecek hastalık id'si
     * @return işlem sonucu
     */
    @Override
    public ResponseEntity<Void> deleteDisease(Long id) {
        diseaseService.deleteDisease(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * İsme göre hastalıkları arar
     * @param name hastalık adı
     * @return bulunan hastalıklar
     */
    @Override
    public ResponseEntity<List<DiseaseResponseDTO>> searchDiseasesByName(String name) {
        List<DiseaseResponseDTO> diseases = diseaseService.searchDiseasesByName(name);
        return ResponseEntity.ok(diseases);
    }

    /**
     * Kategoriye göre hastalıkları arar
     * @param category kategori
     * @return bulunan hastalıklar
     */
    @Override
    public ResponseEntity<List<DiseaseResponseDTO>> getDiseasesByCategory(String category) {
        List<DiseaseResponseDTO> diseases = diseaseService.getDiseasesByCategory(category);
        return ResponseEntity.ok(diseases);
    }

    /**
     * Hayvan türüne göre hastalıkları arar
     * @param animalType hayvan türü
     * @return bulunan hastalıklar
     */
    @Override
    public ResponseEntity<List<DiseaseResponseDTO>> getDiseasesByAnimalType(String animalType) {
        List<DiseaseResponseDTO> diseases = diseaseService.getDiseasesByAnimalType(animalType);
        return ResponseEntity.ok(diseases);
    }
} 