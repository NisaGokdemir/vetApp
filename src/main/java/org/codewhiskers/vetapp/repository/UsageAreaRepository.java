package org.codewhiskers.vetapp.repository;

import org.codewhiskers.vetapp.entity.UsageArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsageAreaRepository extends JpaRepository<UsageArea, Long> {
}