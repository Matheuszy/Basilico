package com.Codexsystem.Basilico.Basilico.cardapio.dto;

import jakarta.validation.constraints.NotNull;

public record BebidaRequestDto(

        @NotNull String nome,
        @NotNull String descricao
) {

}
