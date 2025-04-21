package org.codewhiskers.vetapp.repository;

import org.codewhiskers.vetapp.entity.Specialization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Long> {
    Page<Specialization> findAll(Pageable pageable);
}
