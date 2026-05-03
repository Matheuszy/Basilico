package com.Codexsystem.Basilico.Basilico.catalog.dto;

import jakarta.validation.constraints.NotNull;

public record BebidaRequestDto(

        @NotNull String nome,
        @NotNull String descricao
) {

}
