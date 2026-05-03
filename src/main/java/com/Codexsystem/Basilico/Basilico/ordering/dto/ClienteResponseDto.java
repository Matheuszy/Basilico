package com.Codexsystem.Basilico.Basilico.ordering.dto;

import com.Codexsystem.Basilico.Basilico.ordering.model.Pedido;

import java.util.List;

public record ClienteResponseDto(
        String nome,
        List<Pedido> pedidos
) {
}
