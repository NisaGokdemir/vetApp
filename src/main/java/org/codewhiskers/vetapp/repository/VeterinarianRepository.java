package org.codewhiskers.vetapp.repository;

import org.codewhiskers.vetapp.entity.Veterinarian;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeterinarianRepository extends JpaRepository<Veterinarian, Long> {
    Page<Veterinarian> findAll(Pageable pageable);
}
