package org.codewhiskers.vetapp.repository;

import org.codewhiskers.vetapp.entity.VaccinationBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccinationBatchesRepository extends JpaRepository<VaccinationBatch, Long> {
}