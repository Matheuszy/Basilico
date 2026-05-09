package com.Codexsystem.Basilico.Basilico.ordering.dto.request;

import com.Codexsystem.Basilico.Basilico.catalog.model.Bebida;
import com.Codexsystem.Basilico.Basilico.catalog.model.Refeicao;

import java.util.List;

public record PedidoRequestDto(
        Integer clienteId,
        List<Bebida> bebidas,
        List<Refeicao> refeicoes
) { }
