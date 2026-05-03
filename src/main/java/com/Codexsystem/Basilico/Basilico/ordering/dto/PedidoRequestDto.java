package com.Codexsystem.Basilico.Basilico.ordering.dto;

import com.Codexsystem.Basilico.Basilico.catalog.dto.BebidaResponseDto;
import com.Codexsystem.Basilico.Basilico.catalog.dto.RefeicaoResponseDto;

import java.util.List;

public record PedidoRequestDto(
        List<RefeicaoResponseDto> refeicao,
        List<BebidaResponseDto> bebida
) {
}
