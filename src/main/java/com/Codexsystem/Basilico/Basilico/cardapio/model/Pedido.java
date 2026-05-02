package com.Codexsystem.Basilico.Basilico.cardapio.model;

import com.Codexsystem.Basilico.Basilico.usuario.model.Cliente;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "pedido_refeicoes",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "refeicao_id")
    )
    private List<Refeicao> refeicoes;

    @ManyToMany
    @JoinTable(
            name = "pedido_bebidas",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "bebida_id")
    )
    private List<Bebida> bebidas;

    private BigDecimal valorTotal;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public void calcularValorTotal() {
        BigDecimal totalRefeicoes = refeicoes.stream()
                .map(Refeicao::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalBebidas = bebidas.stream()
                .map(Bebida::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.valorTotal = totalRefeicoes.add(totalBebidas);
    }
}