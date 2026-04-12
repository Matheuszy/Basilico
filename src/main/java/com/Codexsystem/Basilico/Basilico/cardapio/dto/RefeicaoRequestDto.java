package com.Codexsystem.Basilico.Basilico.cardapio.dto;

import jakarta.validation.constraints.NotNull;

public record RefeicaoRequestDto(
        @NotNull String nome,
        @NotNull String descricao
) {
}
