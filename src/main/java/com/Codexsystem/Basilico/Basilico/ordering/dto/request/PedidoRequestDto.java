package com.Codexsystem.Basilico.Basilico.ordering.dto.request;

import com.Codexsystem.Basilico.Basilico.catalog.model.Bebida;
import com.Codexsystem.Basilico.Basilico.catalog.model.Refeicao;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record PedidoRequestDto(
        @NotNull
        Integer clienteId,

        @NotEmpty
        List<Bebida> bebidas,

        @NotEmpty
        List<Refeicao> refeicoes
) { }
