package com.Codexsystem.Basilico.Basilico.management.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RegisterRequestDto(
        @NotEmpty(message = "Nome do usuário não pode estar vazio")
        String username,
        @NotEmpty(message = "Email do usuário não pode estar vazio")
        String email,
        @NotEmpty(message = "Senha do usuário não pode estar vazia")
        String password
) {
}
