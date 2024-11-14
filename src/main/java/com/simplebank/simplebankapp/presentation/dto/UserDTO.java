package com.simplebank.simplebankapp.presentation.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record UserDTO(

        @Nullable
        String name,
        @Nullable
        String lastName,
        @Pattern(regexp = "^[0-9]{8}[A-HJ-NP-TV-Z]$", message = "DNI no válido")
        @Nullable
        String dni,
        @Email(message = "Email no válido", regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
        @Nullable
        String email,
        @Pattern(regexp = "^(?=.*[A-Z])(?=.*[!@#$%^&*()-+=?])(?=.*[0-9a-zA-Z]).{9,}$",
                message = "Contraseña no válida")
        @Nullable
        String password
) {
}
