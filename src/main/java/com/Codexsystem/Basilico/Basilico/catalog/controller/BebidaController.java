package com.Codexsystem.Basilico.Basilico.catalog.controller;

import com.Codexsystem.Basilico.Basilico.catalog.dto.BebidaRequestDto;
import com.Codexsystem.Basilico.Basilico.catalog.dto.BebidaResponseDto;
import com.Codexsystem.Basilico.Basilico.catalog.model.Bebida;
import com.Codexsystem.Basilico.Basilico.catalog.services.BebidaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bebida")
public class BebidaController {

    @Autowired
    private BebidaService bebidaService;

    @PostMapping("/criar/bebida")
    public BebidaResponseDto criarBebida(@RequestBody BebidaRequestDto bebidaRequestDto) {
        Bebida newBebida = new Bebida(bebidaRequestDto.nome(), bebidaRequestDto.descricao(),  bebidaRequestDto.valor());
        bebidaService.criarBebida(newBebida);

        return new BebidaResponseDto(
                newBebida.getNome(),
                newBebida.getDescricao()
        );
    }

    @GetMapping("/obter/bebida")
    public BebidaResponseDto obterBebidaPornome(@RequestParam String nome) {
        var bebida = bebidaService.obterBebidaPorNome(nome).orElse(null);
        return new BebidaResponseDto(bebida.getNome(), bebida.getDescricao());
    }

    @GetMapping("/obter/{id}")
    public BebidaResponseDto obterBebidaPorId(@PathVariable Long id) {
        var bebida = bebidaService.obterBebidaPorId(id);
        return new BebidaResponseDto(bebida.getNome(), bebida.getDescricao());
    }

    @DeleteMapping("/delete/bebida")
    public void deleteBebida(@RequestParam @Valid Long id) {
        bebidaService.deletarBebida(id);
    }
}
