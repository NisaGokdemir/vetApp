package org.codewhiskers.vetapp.repository;

import org.codewhiskers.vetapp.entity.PatientAllergy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientAllergyRepository extends JpaRepository<PatientAllergy, Long> {
    
    /**
     * Belirli bir hastaya ait tüm alerjileri bulur
     * @param patientId hasta ID'si
     * @return hastaya ait alerjiler listesi
     */
    List<PatientAllergy> findByPatientId(Long patientId);
    
    /**
     * Belirli bir alerji adına göre hasta alerjilerini bulur
     * @param allergy alerji adı
     * @return alerji listesi
     */
    List<PatientAllergy> findByAllergyContainingIgnoreCase(String allergy);
    
    /**
     * Belirli bir seviyeye göre hasta alerjilerini bulur
     * @param severity şiddet seviyesi
     * @return alerji listesi
     */
    List<PatientAllergy> findBySeverityContainingIgnoreCase(String severity);
}
