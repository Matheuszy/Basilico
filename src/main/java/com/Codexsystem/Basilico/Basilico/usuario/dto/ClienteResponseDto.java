package com.Codexsystem.Basilico.Basilico.usuario.dto;

import com.Codexsystem.Basilico.Basilico.cardapio.model.Pedido;

import java.util.List;

public record ClienteResponseDto(
        String nome,
        List<Pedido> pedidos
) {
}
