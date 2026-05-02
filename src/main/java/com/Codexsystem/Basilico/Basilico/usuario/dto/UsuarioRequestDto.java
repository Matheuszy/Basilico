package com.Codexsystem.Basilico.Basilico.usuario.dto;

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
