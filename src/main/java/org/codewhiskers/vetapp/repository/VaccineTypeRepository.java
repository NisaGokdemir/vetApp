package org.codewhiskers.vetapp.repository;

import org.codewhiskers.vetapp.entity.VaccineType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccineTypeRepository extends JpaRepository<VaccineType, Long> {
}
