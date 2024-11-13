package com.simplebank.simplebankapp.persistence.repository;

import com.simplebank.simplebankapp.persistence.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(String roleName);
}
