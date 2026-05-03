package com.Codexsystem.Basilico.Basilico.ordering.dto;

import com.Codexsystem.Basilico.Basilico.catalog.model.Bebida;
import com.Codexsystem.Basilico.Basilico.catalog.model.Refeicao;
import com.Codexsystem.Basilico.Basilico.ordering.model.Pedido;

import java.math.BigDecimal;
import java.util.List;

public record PedidoResponseDto(
        List<Refeicao> refeicaos,
        List<Bebida> bebidas,
        BigDecimal totalTotal
) {
}
