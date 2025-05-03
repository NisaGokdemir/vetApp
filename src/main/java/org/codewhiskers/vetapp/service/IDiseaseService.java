package org.codewhiskers.vetapp.service;

import org.codewhiskers.vetapp.dto.Disease.request.DiseaseRequestDTO;
import org.codewhiskers.vetapp.dto.Disease.response.DiseaseResponseDTO;

import java.util.List;

public interface IDiseaseService {

    /**
     * Yeni hastalık ekler
     * @param diseaseRequestDTO hastalık bilgileri
     * @return eklenen hastalık
     */
    DiseaseResponseDTO addDisease(DiseaseRequestDTO diseaseRequestDTO);

    /**
     * Hastalığı günceller
     * @param id güncellenecek hastalık id'si
     * @param diseaseRequestDTO güncellenmiş hastalık bilgileri
     * @return güncellenen hastalık
     */
    DiseaseResponseDTO updateDisease(Long id, DiseaseRequestDTO diseaseRequestDTO);

    /**
     * ID'ye göre hastalığı getirir
     * @param id hastalık id'si
     * @return bulunan hastalık
     */
    DiseaseResponseDTO getDiseaseById(Long id);

    /**
     * Tüm hastalıkları listeler
     * @return hastalık listesi
     */
    List<DiseaseResponseDTO> getAllDiseases();

    /**
     * Hastalığı siler
     * @param id silinecek hastalık id'si
     */
    void deleteDisease(Long id);

    /**
     * İsme göre hastalıkları arar
     * @param name hastalık adı
     * @return bulunan hastalıklar
     */
    List<DiseaseResponseDTO> searchDiseasesByName(String name);

    /**
     * Kategoriye göre hastalıkları arar
     * @param category kategori
     * @return bulunan hastalıklar
     */
    List<DiseaseResponseDTO> getDiseasesByCategory(String category);

    /**
     * Hayvan türüne göre hastalıkları arar
     * @param animalType hayvan türü
     * @return bulunan hastalıklar
     */
    List<DiseaseResponseDTO> getDiseasesByAnimalType(String animalType);
} 