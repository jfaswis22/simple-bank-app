package com.simplebank.simplebankapp.presentation.dto;

import com.simplebank.simplebankapp.util.enums.AccountType;

public record AccountDTO(
        AccountType accountType,
        String currency
) {
}
