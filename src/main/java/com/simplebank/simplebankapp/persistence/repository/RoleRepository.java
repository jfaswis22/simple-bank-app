package com.simplebank.simplebankapp.persistence.repository;

import com.simplebank.simplebankapp.persistence.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
