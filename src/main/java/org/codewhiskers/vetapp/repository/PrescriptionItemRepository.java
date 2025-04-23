package org.codewhiskers.vetapp.repository;

import org.codewhiskers.vetapp.entity.PrescriptionItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface PrescriptionItemRepository extends JpaRepository<PrescriptionItem, Long> {


}
