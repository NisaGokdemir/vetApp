package org.codewhiskers.vetapp.repository;

import org.codewhiskers.vetapp.entity.PatientAllergy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientAllergyRepository extends JpaRepository<PatientAllergy, Long> {
}
