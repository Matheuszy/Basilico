package com.Codexsystem.Basilico.Basilico.mapper.catalog;

import com.Codexsystem.Basilico.Basilico.catalog.dto.request.BebidaRequestDto;
import com.Codexsystem.Basilico.Basilico.catalog.dto.response.BebidaResponseDto;
import com.Codexsystem.Basilico.Basilico.catalog.model.Bebida;
import org.springframework.stereotype.Component;

@Component
public class BebidaMapper {


    public BebidaResponseDto toResponseDto(Bebida bebida) {
        return new BebidaResponseDto(
                bebida.getNome(),
                bebida.getDescricao()
        );
    }

    public Bebida toEntity(BebidaRequestDto bebidaRequestDto) {
        return new Bebida(
                bebidaRequestDto.nome(),
                bebidaRequestDto.descricao(),
                bebidaRequestDto.valor()
        );
    }

}