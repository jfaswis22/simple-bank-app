package com.simplebank.simplebankapp.configuration.security.jwt;

import lombok.Builder;

@Builder
public record AuthResponse(
        String token
) {
}
