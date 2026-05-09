package com.Codexsystem.Basilico.Basilico.catalog.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record RefeicaoRequestDto(
        @NotEmpty(message = "Nome da refeicao não pode estar vazio")
        String nome,
        @NotEmpty(message = "Descrição da refeicao não pode estar vazio")
        String descricao,
        @NotEmpty(message = "Valor da refeicao não pode estar vazio")
        BigDecimal preco
        ) {
}
