package com.Codexsystem.Basilico.Basilico.ordering.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ClienteRequestDto(
        @NotEmpty(message = "Nome não pode estar vazio")
        String nome,
        @NotEmpty(message = "Email não pode estar vazio")
        String email,
        @NotEmpty(message = "Telefone não pode estar vazio")
        String telefone,
        @NotEmpty(message = "Senha não pode estar vazia")
        String senha
) {
}
