package com.simplebank.simplebankapp;

import com.simplebank.simplebankapp.configuration.security.jwt.AuthResponse;
import com.simplebank.simplebankapp.configuration.security.jwt.JwtService;
import com.simplebank.simplebankapp.configuration.security.jwt.SignInRequest;
import com.simplebank.simplebankapp.configuration.security.jwt.SignUpRequest;
import com.simplebank.simplebankapp.persistence.entity.Role;
import com.simplebank.simplebankapp.persistence.entity.User;
import com.simplebank.simplebankapp.persistence.repository.RoleRepository;
import com.simplebank.simplebankapp.persistence.repository.UserRepository;
import com.simplebank.simplebankapp.service.exception.EmailAlreadyExistsException;
import com.simplebank.simplebankapp.service.exception.EmailNotFoundException;
import com.simplebank.simplebankapp.service.exception.RoleNotFoundException;
import com.simplebank.simplebankapp.service.implementation.AuthServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceImplTests {
    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AuthServiceImpl authService;

    @Mock
    private AuthenticationManager authenticationManager;

    private SignUpRequest signUpRequest;
    private SignInRequest signInRequest;

    @BeforeEach
    public void setUp() {
        signUpRequest = SignUpRequest.builder()
                .name("Jhon")
                .dni("35310264W")
                .email("jfaswis2@gmail.com")
                .lastName("Aristizabal")
                .password("C9133201m@")
                .build();

        signInRequest = SignInRequest.builder()
                .email("jfaswis2@gmail.com")
                .password("C9133201m@")
                .build();
    }

    @Test
    public void signIn_ValidCredentials_ReturnsAuthResponse() {
        User user = mock(User.class);
        when(userRepository.findByEmail(signInRequest.email())).thenReturn(Optional.of(user));

        when(authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.email(), signInRequest.password())
        )).thenReturn(null);

        when(jwtService.getToken(user)).thenReturn("mockedToken");

        AuthResponse response = authService.signIn(signInRequest);

        assertNotNull(response);
        assertEquals("mockedToken", response.token());
    }

    @Test
    public void signIn_InvalidPassword_ThrowsAuthenticationException() {
        User user = mock(User.class);

        when(userRepository.findByEmail(signInRequest.email())).thenReturn(Optional.of(user));

        doThrow(new org.springframework.security.core.AuthenticationException("Invalid credentials") {})
                .when(authenticationManager).authenticate(
                        new UsernamePasswordAuthenticationToken(signInRequest.email(), signInRequest.password())
                );

        assertThrows(AuthenticationException.class,
                () -> authService.signIn(signInRequest));
    }



    @Test
    public void signIn_EmailNotFound_ThrowsEmailNotFoundException() {
        when(userRepository.findByEmail(signInRequest.email()))
                .thenReturn(Optional.empty());

        EmailNotFoundException exception = assertThrows(
                EmailNotFoundException.class,
                () -> authService.signIn(signInRequest)
        );

        assertEquals("The email jfaswis2@gmail.com was not found", exception.getMessage());
    }

    @Test
    public void signUp_EmailAlreadyExists_ThrowsEmailAlreadyExistsException() {
        when(userRepository.findByEmail(signUpRequest.email()))
                .thenReturn(Optional.of(new User()));

        EmailAlreadyExistsException exception = assertThrows(
                EmailAlreadyExistsException.class,
                () -> authService.signUp(signUpRequest)
        );

        assertEquals("The email jfaswis2@gmail.com is already in use", exception.getMessage());
    }

    @Test
    public void signUp_RoleNoteFound_ThrowsRoleNotFoundException() {
        when(userRepository.findByEmail(signUpRequest.email())).thenReturn(Optional.empty());
        when(roleRepository.findByRoleName("user")).thenReturn(Optional.empty());

        RoleNotFoundException exception = assertThrows(
                RoleNotFoundException.class,
                () -> authService.signUp(signUpRequest)
        );

        assertEquals("The role USER was not found", exception.getMessage());
    }

    @Test
    public void signUp_ValidRequest_ReturnsAuthResponse() {
        Role role = Role.builder()
                .createdAt(LocalDateTime.now())
                .description("User role")
                .roleName("user")
                .updatedAt(LocalDateTime.now())
                .build();

        when(userRepository.findByEmail(signUpRequest.email())).thenReturn(Optional.empty());
        when(roleRepository.findByRoleName("user")).thenReturn(Optional.of(role));
        when(passwordEncoder.encode(signUpRequest.password())).thenReturn("encodedPassword");
        when(jwtService.getToken(any(User.class))).thenReturn("mockedToken");

        AuthResponse response = authService.signUp(signUpRequest);

        assertNotNull(response);
        assertEquals("mockedToken", response.token());

        verify(userRepository, times(1)).save(any(User.class));
    }
}
