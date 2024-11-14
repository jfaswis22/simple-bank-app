package com.simplebank.simplebankapp.service.implementation;

import com.simplebank.simplebankapp.persistence.entity.User;
import com.simplebank.simplebankapp.persistence.repository.UserRepository;
import com.simplebank.simplebankapp.presentation.dto.UserDTO;
import com.simplebank.simplebankapp.service.exception.AccountNotFoundException;
import com.simplebank.simplebankapp.service.exception.DniAlreadyExistsException;
import com.simplebank.simplebankapp.service.exception.EmailAlreadyExistsException;
import com.simplebank.simplebankapp.service.exception.EmailNotFoundException;
import com.simplebank.simplebankapp.service.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public User findByUser(Authentication authentication) {
        if (userRepository.findByEmail(authentication.getName()).isEmpty()) {
            throw new EmailNotFoundException("The user with email " + authentication.getName() + " was not found");
        }

        return userRepository.findByEmail(authentication.getName()).get();
    }

    @Override
    public List<User> findAllUser() {
        if (userRepository.findAll().isEmpty()) {
            throw new AccountNotFoundException("The user list is empty");
        }

        return userRepository.findAll();
    }

    @Override
    public User updateUser(UserDTO userDTO, Authentication authentication) {
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new EmailNotFoundException("The user with email " + authentication.getName() + " was not found"));

        if (userRepository.findByEmail(userDTO.email()).isPresent()) {
            throw new EmailAlreadyExistsException("The email " + userDTO.email() + " is already in use");
        }

        if (userRepository.findByDni(userDTO.dni()).isPresent()) {
            throw new DniAlreadyExistsException("The dni " + userDTO.dni() + " is already in use");
        }

        if (userDTO.name() != null &&
                userDTO.name().isEmpty() &&
                !Objects.equals(user.getName(), userDTO.name())) {
            user.setName(userDTO.name());
        }

        if (userDTO.lastName() != null &&
                userDTO.lastName().isEmpty() &&
                !Objects.equals(user.getLastName(), userDTO.lastName())) {
            user.setLastName(userDTO.lastName());
        }

        if (userDTO.dni() != null &&
                userDTO.dni().isEmpty() &&
                !Objects.equals(user.getDni(), userDTO.dni())) {
            user.setDni(userDTO.dni());
        }

        if (userDTO.email() != null &&
                userDTO.email().isEmpty() &&
                !Objects.equals(user.getEmail(), userDTO.email())) {
            user.setEmail(userDTO.email());
        }

        if (userDTO.password() != null &&
                userDTO.password().isEmpty() &&
                !passwordEncoder.matches(userDTO.password(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(userDTO.password()));
        }

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Authentication authentication) {
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new EmailNotFoundException("The user with email " + authentication.getName() + " was not found"));
        userRepository.delete(user);
    }
}
