package com.Codexsystem.Basilico.Basilico.catalog.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "bebidas")
public class Bebida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private BigDecimal valor;

    public Bebida() {}
    public Bebida(String nome, String descricao, BigDecimal valor) {
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
    }
}
