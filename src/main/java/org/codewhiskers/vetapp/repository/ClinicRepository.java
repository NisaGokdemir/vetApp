package org.codewhiskers.vetapp.repository;

import org.codewhiskers.vetapp.entity.Clinic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Long> {
    Page<Clinic> findAll(Pageable pageable);

}
