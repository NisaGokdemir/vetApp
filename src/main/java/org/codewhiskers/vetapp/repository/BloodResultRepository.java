package org.codewhiskers.vetapp.repository;

import org.codewhiskers.vetapp.entity.BloodResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloodResultRepository extends JpaRepository<BloodResult, Long> {
}
