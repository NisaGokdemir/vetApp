package org.codewhiskers.vetapp.repository;

import org.codewhiskers.vetapp.dto.User.response.UserResponseDTO;
import org.codewhiskers.vetapp.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findAll(Pageable pageable);

    Optional<User> findByUsername(String username);
}
