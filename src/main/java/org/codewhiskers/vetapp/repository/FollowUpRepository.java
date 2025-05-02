package org.codewhiskers.vetapp.repository;

import org.codewhiskers.vetapp.entity.FollowUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowUpRepository extends JpaRepository<FollowUp, Long> {
}
