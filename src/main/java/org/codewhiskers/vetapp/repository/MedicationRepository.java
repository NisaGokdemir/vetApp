package org.codewhiskers.vetapp.repository;

import org.codewhiskers.vetapp.entity.Medication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {
    Page<Medication> findAll(Pageable pageable);
}
