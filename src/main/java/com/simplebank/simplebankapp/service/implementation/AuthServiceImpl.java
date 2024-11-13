package com.simplebank.simplebankapp.service.implementation;

import com.simplebank.simplebankapp.configuration.security.jwt.AuthResponse;
import com.simplebank.simplebankapp.configuration.security.jwt.SignInRequest;
import com.simplebank.simplebankapp.configuration.security.jwt.SignUpRequest;
import com.simplebank.simplebankapp.persistence.repository.UserRepository;
import com.simplebank.simplebankapp.service.interfaces.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {
    private final UserRepository userRepository;

    @Override
    public AuthResponse signUp(SignUpRequest signUpRequest) {
        return null;
    }

    @Override
    public AuthResponse signIn(SignInRequest signInRequest) {
        return null;
    }
}
