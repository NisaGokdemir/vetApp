package org.codewhiskers.vetapp.repository;

import org.codewhiskers.vetapp.entity.VaccinationInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccinationInventoryRepository extends JpaRepository<VaccinationInventory, Long> {
}