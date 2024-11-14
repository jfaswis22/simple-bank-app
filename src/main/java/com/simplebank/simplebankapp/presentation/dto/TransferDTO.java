package com.simplebank.simplebankapp.presentation.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record TransferDTO(
        @NotNull
        Long fromAccountId,
        @NotNull
        String accountNumber,
        @NotNull
        @Positive
        BigDecimal amount
) {
}
