package org.codewhiskers.vetapp.repository;

import org.codewhiskers.vetapp.entity.Diagnosis;
import org.codewhiskers.vetapp.entity.Prescription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

    Page<Prescription> findByDiagnosis(Diagnosis diagnosis, Pageable pageable);
}
