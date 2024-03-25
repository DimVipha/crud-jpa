package com.example.demomvc.dto;

import jakarta.validation.constraints.NotBlank;

public record ProductEditRequest(
        @NotBlank
        String name,
        Double price
) {
}
