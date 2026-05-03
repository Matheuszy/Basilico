package com.Codexsystem.Basilico.Basilico.catalog.dto;

import jakarta.validation.constraints.NotNull;

public record RefeicaoRequestDto(
        @NotNull String nome,
        @NotNull String descricao
) {
}
