package com.simplebank.simplebankapp.service.interfaces;

public interface IAuthService {
    SignUpResponse signUp(SignUpRequest signUpRequest);
    SignInResponse signIn(SignInRequest signInRequest);
}
