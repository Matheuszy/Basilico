package com.Codexsystem.Basilico.Basilico.usuario.model;

import com.Codexsystem.Basilico.Basilico.cardapio.model.Pedido;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Pedido> pedido;

    @Column(nullable = false)
    private Boolean ativo;

    public Cliente(String nome, String email, String senha, List<Pedido> pedido) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.pedido = pedido;
    }

    public Cliente() {
    }
}
