package com.Codexsystem.Basilico.Basilico.catalog.controller;

import com.Codexsystem.Basilico.Basilico.catalog.dto.request.BebidaRequestDto;
import com.Codexsystem.Basilico.Basilico.catalog.dto.response.BebidaResponseDto;
import com.Codexsystem.Basilico.Basilico.catalog.model.Bebida;
import com.Codexsystem.Basilico.Basilico.catalog.services.BebidaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/bebida")
public class BebidaController {

    @Autowired
    private BebidaService bebidaService;

    @PostMapping("/criar/bebida")
    public ResponseEntity<BebidaResponseDto> criarBebida(@RequestBody BebidaRequestDto bebidaRequestDto) {
        return bebidaService.criarBebida(bebidaRequestDto);
    }

    @GetMapping("/obter/bebida")
    public ResponseEntity<Optional<BebidaResponseDto>> obterBebidaPornome(@RequestParam String nome) {
        return bebidaService.obterBebidaPorNome(nome);
    }

    @GetMapping("/obter/{id}")
    public ResponseEntity<BebidaResponseDto> obterBebidaPorId(@PathVariable Long id) {
        return bebidaService.obterBebidaPorId(id);
    }

    @DeleteMapping("/delete/bebida")
    public void deleteBebida(@RequestParam @Valid Long id) {
        bebidaService.deletarBebida(id);
    }

    @PatchMapping("update/bebida")
    public ResponseEntity<BebidaResponseDto> updateBebida(Long id,
                                                          @RequestBody
                                                          @Valid
                                                          BebidaRequestDto
                                                                  bebidaRequestDto) {
        return bebidaService.updateBebida(id, bebidaRequestDto);
    }
}
