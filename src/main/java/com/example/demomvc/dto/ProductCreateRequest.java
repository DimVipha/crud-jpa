package com.example.demomvc.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record ProductCreateRequest(
        @NotBlank
        String name,
        @Positive
        Double price,
        @Positive
        Integer qty
) {
}
