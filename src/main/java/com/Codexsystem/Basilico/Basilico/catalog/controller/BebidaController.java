package com.Codexsystem.Basilico.Basilico.catalog.controller;

import com.Codexsystem.Basilico.Basilico.catalog.dto.BebidaRequestDto;
import com.Codexsystem.Basilico.Basilico.catalog.dto.BebidaResponseDto;
import com.Codexsystem.Basilico.Basilico.catalog.services.BebidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bebida")
public class BebidaController {

    @Autowired
    private BebidaService bebidaService;

    @PostMapping("/criar/bebida")
    public BebidaResponseDto criarBebida(@RequestBody BebidaRequestDto bebidaRequestDto) {
        return new BebidaResponseDto(
                bebidaRequestDto.nome(),
                bebidaRequestDto.descricao()
        );
    }

    @GetMapping("/obter/bebida")
    public BebidaResponseDto obterBebidaPornome(@RequestParam String nome) {
        var bebida = bebidaService.obterBebidaPorNome(nome).orElse(null);
        return new BebidaResponseDto(bebida.getNome(), bebida.getDescricao());
    }

    @GetMapping("/obter/{id}")
    public BebidaResponseDto obterBebidaPorId(@PathVariable String id) {
        var bebida = bebidaService.obterBebidaPorId(Long.parseLong(id));
        return new BebidaResponseDto(bebida.getNome(), bebida.getDescricao());
    }
}
