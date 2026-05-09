package com.Codexsystem.Basilico.Basilico.catalog.services;

import com.Codexsystem.Basilico.Basilico.catalog.model.Bebida;
import com.Codexsystem.Basilico.Basilico.catalog.repository.BebidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BebidaService {
    @Autowired
    private BebidaRepository bebidaRepository;

    public Bebida criarBebida(Bebida bebida) {
        if (bebida.getNome() == null || bebida.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da bebida não pode ser nulo ou vazio.");
        } else if (!bebida.getNome().matches("^[a-zA-Z0-9 ]+$")) {
            throw new IllegalArgumentException("O nome da bebida deve conter apenas letras, números e espaços.");
        } else if (bebida.getPreco() == null || bebida.getPreco().compareTo(new java.math.BigDecimal("0.00")) <= 0) {
            throw new RuntimeException("O preço da bebida deve ser maior que zero.");

        } else if (bebida.getDescricao() == null || bebida.getDescricao().trim().isEmpty()) {
            throw new RuntimeException("A descrição da bebida não pode ser nula ou vazia.");

        }

        return bebidaRepository.save(bebida);
    }

     public Bebida obterBebidaPorId(Long id) {
         return bebidaRepository.findById(id).orElse(null);
     }

     public Optional<Bebida> obterBebidaPorNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da bebida não pode ser nulo ou vazio.");
        } else if (!nome.matches("^[a-zA-Z0-9 ]+$")) {
            throw new IllegalArgumentException("O nome da bebida deve conter apenas letras, números e espaços.");
        }
        return bebidaRepository.findBebidaByNome(nome);
     }

     public void deletarBebida(Long id) {
        if (bebidaRepository.existsById(id)) {
            bebidaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Bebida com ID " + id + " não encontrada.");
        }
     }

}
