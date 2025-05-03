package org.codewhiskers.vetapp.repository;

import org.codewhiskers.vetapp.entity.Drug;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugRepository extends JpaRepository<Drug, Long> {
}
