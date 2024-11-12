package com.simplebank.simplebankapp.persistence.repository;

import com.simplebank.simplebankapp.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
