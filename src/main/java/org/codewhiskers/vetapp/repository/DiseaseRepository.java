package org.codewhiskers.vetapp.repository;

import org.codewhiskers.vetapp.entity.Disease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiseaseRepository extends JpaRepository<Disease, Long> {

    /**
     * İsme göre hastalıkları bulur
     * @param name hastalık adı
     * @return hastalık listesi
     */
    List<Disease> findByNameContainingIgnoreCase(String name);
    
    /**
     * Kategoriye göre hastalıkları bulur
     * @param category kategori
     * @return hastalık listesi
     */
    List<Disease> findByCategoryContainingIgnoreCase(String category);
    
    /**
     * Hayvan türlerine göre hastalıkları bulur
     * @param animalType hayvan türü
     * @return hastalık listesi
     */
    List<Disease> findByAnimalTypesContainingIgnoreCase(String animalType);
} 