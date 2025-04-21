package org.codewhiskers.vetapp.repository;

import org.codewhiskers.vetapp.entity.BloodType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloodTypeRepository extends JpaRepository<BloodType, Long> {
}
