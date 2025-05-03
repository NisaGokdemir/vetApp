package org.codewhiskers.vetapp.repository;

import org.codewhiskers.vetapp.entity.VaccineList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccineListRepository extends JpaRepository<VaccineList, Long> {
}
