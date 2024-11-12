package com.simplebank.simplebankapp.service.implementation;

import com.simplebank.simplebankapp.persistence.repository.UserRepository;
import com.simplebank.simplebankapp.service.interfaces.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {
    private final UserRepository userRepository;
    @Override
    public SignUpResponse signUp(SignUpRequest signUpRequest) {
        return null;
    }

    @Override
    public SignInResponse signIn(SignInRequest signInRequest) {
        return null;
    }
}
