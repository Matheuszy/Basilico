package com.Codexsystem.Basilico.Basilico.ordering.dto.request;

public record EnderecoDto(
        String rua,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String estado,
        String cep
) {}
