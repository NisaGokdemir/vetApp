package org.codewhiskers.vetapp.repository;

import org.codewhiskers.vetapp.entity.DrugList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugListRepository extends JpaRepository<DrugList, Long> {
}