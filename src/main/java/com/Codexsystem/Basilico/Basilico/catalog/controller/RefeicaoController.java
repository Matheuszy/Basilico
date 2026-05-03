package com.Codexsystem.Basilico.Basilico.catalog.controller;

import com.Codexsystem.Basilico.Basilico.catalog.dto.RefeicaoRequestDto;
import com.Codexsystem.Basilico.Basilico.catalog.dto.RefeicaoResponseDto;
import com.Codexsystem.Basilico.Basilico.catalog.model.Refeicao;
import com.Codexsystem.Basilico.Basilico.catalog.services.RefeicaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/refeicao")
public class RefeicaoController {

    @Autowired
    private RefeicaoService refeicaoService;

    @PostMapping("/criar/refeicao")
    public RefeicaoResponseDto criarRefeicao(@RequestBody @Valid RefeicaoRequestDto refeicaoRequestDto) {
        new RefeicaoRequestDto(
                refeicaoRequestDto.nome(),
                refeicaoRequestDto.descricao()
        );
        return new RefeicaoResponseDto(
                refeicaoRequestDto.nome(),
                refeicaoRequestDto.descricao()
        );
    }

    @GetMapping("/obter/refeicao")
    public RefeicaoResponseDto obterRefeicaoPornome(@RequestParam @Valid String nome) {
        Refeicao refeicao = refeicaoService.obterRefeicaoPorNome(nome).orElse(null);
        return new RefeicaoResponseDto(refeicao.getNome(), refeicao.getDescricao());

    }

    @GetMapping("/obter/{id}")
    public RefeicaoResponseDto obterRefeicaoPorId(@PathVariable Long id) {
        Refeicao refeicao = refeicaoService.obterRefeicaoPorId(id);

       return new RefeicaoResponseDto(refeicao.getNome(), refeicao.getDescricao());
    }

}
