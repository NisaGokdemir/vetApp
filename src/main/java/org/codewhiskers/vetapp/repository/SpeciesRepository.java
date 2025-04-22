package org.codewhiskers.vetapp.repository;

import org.codewhiskers.vetapp.entity.Species;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeciesRepository extends JpaRepository<Species, Long> {
}
