package com.Codexsystem.Basilico.Basilico.ordering.dto;

import jakarta.validation.constraints.NotNull;

public record ClienteRequestDto(
        @NotNull
        String nome,
        @NotNull
        String email,
        @NotNull
        String telefone,
        @NotNull
        String senha
) {
}
