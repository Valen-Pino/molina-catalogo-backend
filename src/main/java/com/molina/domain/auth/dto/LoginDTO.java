package com.molina.domain.auth.dto;

public record LoginDTO(
        String usuario,
        String password
) {}