package org.codewhiskers.vetapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.dto.Disease.request.DiseaseRequestDTO;
import org.codewhiskers.vetapp.dto.Disease.response.DiseaseResponseDTO;
import org.codewhiskers.vetapp.entity.Disease;
import org.codewhiskers.vetapp.mapper.DiseaseMapper;
import org.codewhiskers.vetapp.repository.DiseaseRepository;
import org.codewhiskers.vetapp.service.IDiseaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiseaseServiceImpl implements IDiseaseService {

    private final DiseaseRepository diseaseRepository;
    private final DiseaseMapper diseaseMapper;

    @Override
    @Transactional
    public DiseaseResponseDTO addDisease(DiseaseRequestDTO diseaseRequestDTO) {
        // DTO'dan entity'ye çevir
        Disease disease = diseaseMapper.requestDTOToDisease(diseaseRequestDTO);
        
        // Veritabanına kaydet
        Disease savedDisease = diseaseRepository.save(disease);
        
        // Entity'yi response DTO'ya çevir ve döndür
        return diseaseMapper.diseaseToResponseDTO(savedDisease);
    }

    @Override
    @Transactional
    public DiseaseResponseDTO updateDisease(Long id, DiseaseRequestDTO diseaseRequestDTO) {
        // Hastalığı bul
        Disease disease = diseaseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hastalık bulunamadı: " + id));
        
        // DTO'dan gelen verilerle güncelle
        diseaseMapper.updateDiseaseFromRequestDTO(diseaseRequestDTO, disease);
        
        // Veritabanına kaydet
        Disease updatedDisease = diseaseRepository.save(disease);
        
        // Entity'yi response DTO'ya çevir ve döndür
        return diseaseMapper.diseaseToResponseDTO(updatedDisease);
    }

    @Override
    public DiseaseResponseDTO getDiseaseById(Long id) {
        // Hastalığı bul
        Disease disease = diseaseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hastalık bulunamadı: " + id));
        
        // Entity'yi response DTO'ya çevir ve döndür
        return diseaseMapper.diseaseToResponseDTO(disease);
    }

    @Override
    public List<DiseaseResponseDTO> getAllDiseases() {
        // Tüm hastalıkları getir
        return diseaseRepository.findAll().stream()
                .map(diseaseMapper::diseaseToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteDisease(Long id) {
        // Hastalığı bul
        Disease disease = diseaseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hastalık bulunamadı: " + id));
        
        // Veritabanından sil
        diseaseRepository.delete(disease);
    }

    @Override
    public List<DiseaseResponseDTO> searchDiseasesByName(String name) {
        // İsme göre ara
        return diseaseRepository.findByNameContainingIgnoreCase(name).stream()
                .map(diseaseMapper::diseaseToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DiseaseResponseDTO> getDiseasesByCategory(String category) {
        // Kategoriye göre ara
        return diseaseRepository.findByCategoryContainingIgnoreCase(category).stream()
                .map(diseaseMapper::diseaseToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DiseaseResponseDTO> getDiseasesByAnimalType(String animalType) {
        // Hayvan türüne göre ara
        return diseaseRepository.findByAnimalTypesContainingIgnoreCase(animalType).stream()
                .map(diseaseMapper::diseaseToResponseDTO)
                .collect(Collectors.toList());
    }
} 