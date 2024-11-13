package com.simplebank.simplebankapp.service.implementation;

import com.simplebank.simplebankapp.persistence.entity.User;
import com.simplebank.simplebankapp.persistence.repository.UserRepository;
import com.simplebank.simplebankapp.presentation.dto.UserDTO;
import com.simplebank.simplebankapp.service.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    @Override
    public User findByUserId(Long id) {
        return null;
    }

    @Override
    public List<User> findAllUser() {
        return null;
    }

    @Override
    public User updateUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }
}
