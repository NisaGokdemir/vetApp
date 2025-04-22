package org.codewhiskers.vetapp.repository;

import org.codewhiskers.vetapp.entity.MedicationBatch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationBatchRepository extends JpaRepository<MedicationBatch, Long> {
    //Page<MedicationBatch> findAll(Pageable pageable);
}
