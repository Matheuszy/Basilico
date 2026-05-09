package com.Codexsystem.Basilico.Basilico.catalog.controller;

import com.Codexsystem.Basilico.Basilico.catalog.dto.request.RefeicaoRequestDto;
import com.Codexsystem.Basilico.Basilico.catalog.dto.response.RefeicaoResponseDto;
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
        Refeicao newRefeicao = new Refeicao(refeicaoRequestDto.nome(), refeicaoRequestDto.descricao(), refeicaoRequestDto.preco());
        refeicaoService.criarRefeicao(newRefeicao);

        return new RefeicaoResponseDto(newRefeicao.getNome(), newRefeicao.getDescricao());
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

    @DeleteMapping("/delete/refeicao")
    public void deleteRefeicao(@RequestParam @Valid Long id) {
        refeicaoService.deletarRefeicao(id);
    }

    @PatchMapping("/update/refeicao")
    public RefeicaoResponseDto updateRefeicao(@RequestParam @Valid Long id, @RequestBody RefeicaoRequestDto refeicaoRequestDto) {
        var refeicao = refeicaoService.obterRefeicaoPorId(id);
        refeicao.setNome(refeicaoRequestDto.nome());
        refeicao.setDescricao(refeicaoRequestDto.descricao());
        refeicao.setValor(refeicaoRequestDto.preco());
        refeicaoService.updateRefeicao(id, refeicao);

        return new RefeicaoResponseDto(refeicao.getNome(), refeicao.getDescricao());
    }

}
