package com.Codexsystem.Basilico.Basilico.ordering.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record EnderecoDto(
        @NotEmpty(message = "O campo não pode estar vazio")
        String rua,
        @NotEmpty(message = "O campo não pode estar vazio")
        String numero,
        String complemento,
        @NotEmpty(message = "O campo não pode estar vazio")
        String bairro,
        @NotEmpty(message = "O campo não pode estar vazio")
        String cidade,
        @NotEmpty(message = "O campo não pode estar vazio")
        String estado,
        @NotEmpty(message = "O campo não pode estar vazio")
        String cep
) {}
