package com.Codexsystem.Basilico.Basilico.ordering.dto;

import com.Codexsystem.Basilico.Basilico.catalog.dto.BebidaResponseDto;
import com.Codexsystem.Basilico.Basilico.catalog.dto.RefeicaoResponseDto;
import com.Codexsystem.Basilico.Basilico.catalog.model.Bebida;
import com.Codexsystem.Basilico.Basilico.catalog.model.Refeicao;

import java.math.BigDecimal;
import java.util.List;

public record PedidoRequestDto(
        Integer clienteId,
        List<Bebida> bebidas,
        List<Refeicao> refeicoes
) { }
