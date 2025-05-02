package org.codewhiskers.vetapp.repository;

import org.codewhiskers.vetapp.entity.RadiologicImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RadiologicImageRepository extends JpaRepository<RadiologicImage, Long> {
}
