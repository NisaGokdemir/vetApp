package org.codewhiskers.vetapp.repository;

import org.codewhiskers.vetapp.entity.VaccineProtocols;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccineProtocolsRepository extends JpaRepository<VaccineProtocols, Long> {
}