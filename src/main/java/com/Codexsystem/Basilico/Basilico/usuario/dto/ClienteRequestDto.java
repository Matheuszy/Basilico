package com.Codexsystem.Basilico.Basilico.usuario.dto;

import jakarta.validation.constraints.NotNull;

public record ClienteRequestDto(
        @NotNull
        String nome,
        @NotNull
        String email,
        @NotNull
        String senha
) {
}
