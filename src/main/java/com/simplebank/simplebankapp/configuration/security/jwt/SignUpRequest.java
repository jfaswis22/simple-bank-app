package com.simplebank.simplebankapp.configuration.security.jwt;

import jakarta.validation.Constraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record SignUpRequest(

        @NotBlank(message = "El nombre no debe de ser nulo, vacio ni contener solo espacios en blanco")
        String name,
        @NotBlank(message = "Los apellidos no deben de ser nulo, vacios ni contener solo espacios en blanco")
        String lastName,
        @Pattern(regexp = "^[0-9]{8}[A-HJ-NP-TV-Z]$", message = "DNI no válido")
        String dni,
        @Email(message = "Email no válido", regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
        String email,
        @Pattern(regexp = "^(?=.*[A-Z])(?=.*[!@#$%^&*()-+=?])(?=.*[0-9a-zA-Z]).{9,}$",
                message = "La contraseña debe tener al menos 9 caracteres, " +
                        "debe incluir al menos una letra en mayúscula, " +
                        "al menos un carácter especial y " +
                        "no tener espacios en blanco.")
        String password
) {
}
