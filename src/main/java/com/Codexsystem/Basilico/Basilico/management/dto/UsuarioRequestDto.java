package com.Codexsystem.Basilico.Basilico.management.dto;

import jakarta.validation.constraints.NotNull;

public record UsuarioRequestDto(
        @NotNull
        String username,
        @NotNull
        String email,
        @NotNull
        String password
) {
}
