package com.simplebank.simplebankapp.service.interfaces;

import com.simplebank.simplebankapp.persistence.entity.User;
import com.simplebank.simplebankapp.presentation.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUserService {
    User findByUserId(Long id);
    List<User> findAllUser();
    User updateUser(UserDTO userDTO);
    void deleteUser(Long id);
}
