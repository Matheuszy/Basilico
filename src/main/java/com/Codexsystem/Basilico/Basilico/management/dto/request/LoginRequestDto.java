package com.Codexsystem.Basilico.Basilico.management.dto.request;

import jakarta.validation.constraints.NotNull;

public record LoginRequestDto(
        @NotNull String username,
        @NotNull
        String email,
        @NotNull
        String senha
) {
}
