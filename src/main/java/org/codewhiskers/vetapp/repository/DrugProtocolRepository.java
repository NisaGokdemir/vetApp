package org.codewhiskers.vetapp.repository;

import org.codewhiskers.vetapp.entity.DrugProtocol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugProtocolRepository extends JpaRepository<DrugProtocol, Long> {
}