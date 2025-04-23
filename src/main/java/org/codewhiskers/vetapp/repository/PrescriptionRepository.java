package org.codewhiskers.vetapp.repository;

import org.codewhiskers.vetapp.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
}
