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
        @NotEmpty(message = "CPF não pode estar vazio")
        String cpf,
        @NotEmpty(message = "Senha não pode estar vazia")
        String senha,
        @NotEmpty(message = "Senha não pode estar vazia")
        EnderecoDto endereco
) {
}
