package org.codewhiskers.vetapp.controller;

import org.codewhiskers.vetapp.dto.Disease.request.DiseaseRequestDTO;
import org.codewhiskers.vetapp.dto.Disease.response.DiseaseResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IRestDiseaseController {

    /**
     * Yeni hastalık ekler
     * @param diseaseRequestDTO hastalık bilgileri
     * @return eklenen hastalık
     */
    @PostMapping
    ResponseEntity<DiseaseResponseDTO> addDisease(@RequestBody DiseaseRequestDTO diseaseRequestDTO);

    /**
     * Hastalığı günceller
     * @param id güncellenecek hastalık id'si
     * @param diseaseRequestDTO güncellenmiş hastalık bilgileri
     * @return güncellenen hastalık
     */
    @PutMapping("/{id}")
    ResponseEntity<DiseaseResponseDTO> updateDisease(
            @PathVariable Long id, 
            @RequestBody DiseaseRequestDTO diseaseRequestDTO);

    /**
     * ID'ye göre hastalığı getirir
     * @param id hastalık id'si
     * @return bulunan hastalık
     */
    @GetMapping("/{id}")
    ResponseEntity<DiseaseResponseDTO> getDiseaseById(@PathVariable Long id);

    /**
     * Tüm hastalıkları listeler
     * @return hastalık listesi
     */
    @GetMapping
    ResponseEntity<List<DiseaseResponseDTO>> getAllDiseases();

    /**
     * Hastalığı siler
     * @param id silinecek hastalık id'si
     * @return işlem sonucu
     */
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteDisease(@PathVariable Long id);

    /**
     * İsme göre hastalıkları arar
     * @param name hastalık adı
     * @return bulunan hastalıklar
     */
    @GetMapping("/search")
    ResponseEntity<List<DiseaseResponseDTO>> searchDiseasesByName(@RequestParam String name);

    /**
     * Kategoriye göre hastalıkları arar
     * @param category kategori
     * @return bulunan hastalıklar
     */
    @GetMapping("/category/{category}")
    ResponseEntity<List<DiseaseResponseDTO>> getDiseasesByCategory(@PathVariable String category);

    /**
     * Hayvan türüne göre hastalıkları arar
     * @param animalType hayvan türü
     * @return bulunan hastalıklar
     */
    @GetMapping("/animal-type/{animalType}")
    ResponseEntity<List<DiseaseResponseDTO>> getDiseasesByAnimalType(@PathVariable String animalType);
} 