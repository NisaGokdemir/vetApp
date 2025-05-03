package org.codewhiskers.vetapp.repository;

import org.codewhiskers.vetapp.entity.RadiologicImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RadiologicImageRepository extends JpaRepository<RadiologicImage, Long> {
    /**
     * Hasta ID'sine göre radyolojik görüntüleri bulur
     * @param patientId hasta id'si
     * @return radyolojik görüntü listesi
     */
    List<RadiologicImage> findByPatientId(Long patientId);
    
    /**
     * Klinik ID'sine göre radyolojik görüntüleri bulur
     * @param clinicId klinik id'si
     * @return radyolojik görüntü listesi
     */
    List<RadiologicImage> findByClinicId(Long clinicId);
    
    /**
     * Test adına göre radyolojik görüntüleri bulur
     * @param testName test adı
     * @return radyolojik görüntü listesi
     */
    List<RadiologicImage> findByTestNameContainingIgnoreCase(String testName);
}
