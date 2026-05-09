package com.Codexsystem.Basilico.Basilico.catalog.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record BebidaRequestDto(

        @NotEmpty(message = "Nome da bebida não pode estar vazio")
        String nome,
        @NotEmpty(message = "Descrição da bebida não pode estar vazio")
        String descricao,
        @NotEmpty(message = "Valor da bebida não pode estar vazio")
        BigDecimal valor
) {

}
