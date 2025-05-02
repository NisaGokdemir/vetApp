package org.codewhiskers.vetapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientAllergy extends JpaRepository<PatientAllergy, Long> {
}
