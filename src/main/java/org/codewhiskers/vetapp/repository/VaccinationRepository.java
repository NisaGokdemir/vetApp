package org.codewhiskers.vetapp.repository;

import org.codewhiskers.vetapp.entity.Vaccination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccinationRepository extends JpaRepository<Vaccination, Long> {
}
