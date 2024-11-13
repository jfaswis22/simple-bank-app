package com.simplebank.simplebankapp.service.implementation;

import com.simplebank.simplebankapp.configuration.security.jwt.AuthResponse;
import com.simplebank.simplebankapp.configuration.security.jwt.JwtService;
import com.simplebank.simplebankapp.configuration.security.jwt.SignInRequest;
import com.simplebank.simplebankapp.configuration.security.jwt.SignUpRequest;
import com.simplebank.simplebankapp.persistence.entity.User;
import com.simplebank.simplebankapp.persistence.repository.RoleRepository;
import com.simplebank.simplebankapp.persistence.repository.UserRepository;
import com.simplebank.simplebankapp.service.exception.EmailAlreadyExistsException;
import com.simplebank.simplebankapp.service.exception.EmailNotFoundException;
import com.simplebank.simplebankapp.service.exception.RoleNotFoundException;
import com.simplebank.simplebankapp.service.interfaces.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;

    @Override
    public AuthResponse signUp(SignUpRequest signUpRequest) {
        if (userRepository.findByEmail(signUpRequest.email()).isPresent()) {
            throw new EmailAlreadyExistsException("The email " + signUpRequest.email() + " is already in use");
        }

        if (roleRepository.findByRoleName("user").isEmpty()) {
            throw new RoleNotFoundException("The role USER was not found");
        }

        User user = User.builder()
                .dni(signUpRequest.dni())
                .role(roleRepository.findByRoleName("user").get())
                .name(signUpRequest.name())
                .lastName(signUpRequest.lastName())
                .email(signUpRequest.email())
                .password(passwordEncoder.encode(signUpRequest.password()))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }

    @Override
    public AuthResponse signIn(SignInRequest signInRequest) {
        UserDetails user = userRepository.findByEmail(signInRequest.email())
                .orElseThrow(() -> new EmailNotFoundException("The email " + signInRequest.email() + " was not found"));

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.email(), signInRequest.password()));

        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }
}
