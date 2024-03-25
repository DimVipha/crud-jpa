package com.example.demomvc.dto;

public record ProductResponse(
        String name,
        String uuid,
        Double price,
        Integer qty
) {
}
