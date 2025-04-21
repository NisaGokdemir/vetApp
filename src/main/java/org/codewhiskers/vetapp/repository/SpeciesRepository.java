package org.codewhiskers.vetapp.repository;

import org.codewhiskers.vetapp.entity.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeciesRepository extends JpaRepository<Species, Long> {
}
