package com.simplebank.simplebankapp.service.interfaces;

import com.simplebank.simplebankapp.configuration.security.jwt.AuthResponse;
import com.simplebank.simplebankapp.configuration.security.jwt.SignInRequest;
import com.simplebank.simplebankapp.configuration.security.jwt.SignUpRequest;

public interface IAuthService {
    AuthResponse signUp(SignUpRequest signUpRequest);
    AuthResponse signIn(SignInRequest signInRequest);
}
