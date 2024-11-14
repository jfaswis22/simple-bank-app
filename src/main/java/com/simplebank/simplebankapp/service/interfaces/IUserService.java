package com.simplebank.simplebankapp.service.interfaces;

import com.simplebank.simplebankapp.persistence.entity.User;
import com.simplebank.simplebankapp.presentation.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface IUserService {
    User findByUser(Authentication authentication);
    List<User> findAllUser();
    User updateUser(UserDTO userDTO, Authentication authentication);
    void deleteUser(Authentication authentication);
}
