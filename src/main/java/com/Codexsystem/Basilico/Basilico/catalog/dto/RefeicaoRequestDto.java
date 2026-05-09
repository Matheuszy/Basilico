package com.Codexsystem.Basilico.Basilico.catalog.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record RefeicaoRequestDto(
        @NotNull String nome,
        @NotNull String descricao,
        @NotNull BigDecimal preco
        ) {
}
