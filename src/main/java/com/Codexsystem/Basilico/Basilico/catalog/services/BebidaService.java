package com.Codexsystem.Basilico.Basilico.catalog.services;

import com.Codexsystem.Basilico.Basilico.catalog.dto.request.BebidaRequestDto;
import com.Codexsystem.Basilico.Basilico.catalog.dto.response.BebidaResponseDto;
import com.Codexsystem.Basilico.Basilico.catalog.model.Bebida;
import com.Codexsystem.Basilico.Basilico.catalog.repository.BebidaRepository;
import com.Codexsystem.Basilico.Basilico.mapper.catalog.BebidaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BebidaService {
    @Autowired
    private BebidaRepository bebidaRepository;

    private BebidaMapper bebidaMapper;

    public ResponseEntity<BebidaResponseDto> criarBebida(BebidaRequestDto bebidaDto) {
        if (bebidaDto.nome() == null || bebidaDto.nome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da bebida não pode ser nulo ou vazio.");
        } else if (!bebidaDto.nome().matches("^[a-zA-Z0-9 ]+$")) {
            throw new IllegalArgumentException("O nome da bebida deve conter apenas letras, números e espaços.");
        } else if (bebidaDto.valor() == null || bebidaDto.valor().compareTo(new java.math.BigDecimal("0.00")) <= 0) {
            throw new RuntimeException("O preço da bebida deve ser maior que zero.");

        } else if (bebidaDto.descricao() == null || bebidaDto.descricao().trim().isEmpty()) {
            throw new RuntimeException("A descrição da bebida não pode ser nula ou vazia.");
        }

        bebidaRepository.save(bebidaMapper.toEntity(bebidaDto));

        return ResponseEntity.status(201)
                .body(bebidaMapper.toResponseDto(bebidaMapper.toEntity(bebidaDto)));

    }

    public ResponseEntity<BebidaResponseDto> updateBebida(Long id, BebidaRequestDto bebidaAtualizada) {
        Bebida bebidaExistente = bebidaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bebida com ID " + id + " não encontrada."));

        if (bebidaAtualizada.nome() != null && !bebidaAtualizada.nome().trim().isEmpty()) {
            if (!bebidaAtualizada.nome().matches("^[a-zA-Z0-9 ]+$")) {
                throw new IllegalArgumentException("O nome da bebida deve conter apenas letras, números e espaços.");
            }
            bebidaExistente.setNome(bebidaAtualizada.nome());
        }


        if (bebidaAtualizada.valor() != null) {
            if (bebidaAtualizada.valor().compareTo(new java.math.BigDecimal("0.00")) <= 0) {
                throw new RuntimeException("O preço da bebida deve ser maior que zero.");
            }
            bebidaExistente.setValor(bebidaAtualizada.valor());
        }

        if (bebidaAtualizada.descricao() != null && !bebidaAtualizada.descricao().trim().isEmpty()) {
            bebidaExistente.setDescricao(bebidaAtualizada.descricao());
        }

        bebidaRepository.save(bebidaExistente);
        return ResponseEntity.ok(bebidaMapper.toResponseDto(bebidaExistente));
    }

     public ResponseEntity<BebidaResponseDto> obterBebidaPorId(Long id) {
        return ResponseEntity.ok(
                bebidaMapper.toResponseDto
                        (bebidaRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Bebida com não encontrada."))));
     }

     public ResponseEntity<Optional<BebidaResponseDto>> obterBebidaPorNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da bebida não pode ser nulo ou vazio.");
        } else if (!nome.matches("^[a-zA-Z0-9 ]+$")) {
            throw new IllegalArgumentException("O nome da bebida deve conter apenas letras, números e espaços.");
        }
        return ResponseEntity.ok(bebidaRepository.findBebidaByNome(nome).map(bebidaMapper::toResponseDto));
     }

     public void deletarBebida(Long id) {
        if (bebidaRepository.existsById(id)) {
            bebidaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Bebida com ID " + id + " não encontrada.");
        }
     }

}
