package org.codewhiskers.vetapp.repository;

import org.codewhiskers.vetapp.entity.Role;
import org.codewhiskers.vetapp.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleType name);
}