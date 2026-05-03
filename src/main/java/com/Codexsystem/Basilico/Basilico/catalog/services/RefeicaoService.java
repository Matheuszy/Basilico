package com.Codexsystem.Basilico.Basilico.catalog.services;


import com.Codexsystem.Basilico.Basilico.catalog.model.Refeicao;
import com.Codexsystem.Basilico.Basilico.catalog.repository.RefeicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RefeicaoService {
    @Autowired
    private RefeicaoRepository refeicaoRepository;

    public Refeicao criarRefeicao(Refeicao refeicao) {
        if (refeicao.getNome() == null || refeicao.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da refeição não pode ser nulo ou vazio.");
        } else if (!refeicao.getNome().matches("^[a-zA-Z0-9 ]+$")) {
            throw new IllegalArgumentException("O nome da refeição deve conter apenas letras, números e espaços.");
        } else if (refeicao.getDescricao() == null || refeicao.getDescricao().trim().isEmpty()) {
            throw new RuntimeException("A descrição da refeição não pode ser nula ou vazia.");
            
        } else if (refeicao.getValor() == null || refeicao.getValor().compareTo(new java.math.BigDecimal("0.00")) <= 0) {
            throw new RuntimeException("O valor da refeição deve ser maior que zero.");
            
        }
        return refeicaoRepository.save(refeicao);
    }
    
    public Refeicao obterRefeicaoPorId(Long id) {
        return refeicaoRepository.findById(id).orElse(null);
    }
    
    public Optional<Refeicao> obterRefeicaoPorNome(String nome) {
        
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da refeição não pode ser nulo ou vazio.");
        } else if (!nome.matches("^[a-zA-Z0-9 ]+$")) {
            throw new IllegalArgumentException("O nome da refeição deve conter apenas letras, números e espaços.");
            
        }
        return refeicaoRepository.findByNome(nome);
    }

}
