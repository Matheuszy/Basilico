package com.Codexsystem.Basilico.Basilico.cardapio.services;

import com.Codexsystem.Basilico.Basilico.cardapio.model.Bebida;
import com.Codexsystem.Basilico.Basilico.cardapio.model.Pedido;
import com.Codexsystem.Basilico.Basilico.cardapio.model.Refeicao;
import com.Codexsystem.Basilico.Basilico.cardapio.repository.PedidoRepository;
import com.Codexsystem.Basilico.Basilico.usuario.model.Cliente;
import com.Codexsystem.Basilico.Basilico.usuario.repository.ClienteRepository;
import com.Codexsystem.Basilico.Basilico.usuario.services.ClienteService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public Pedido criarPedido(Integer clienteId, List<Bebida> bebida, List<Refeicao> refeicao) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Pedido pedido = new Pedido();
        pedido.setRefeicoes(refeicao);
        pedido.setBebidas(bebida);
        pedido.calcularValorTotal();

        cliente.criarPedido(pedido);

        return pedidoRepository.save(pedido);
    }

    @Transactional
    public Pedido updateRefeicao(Long pedidoId, List<Refeicao> refeicao) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        pedido.setRefeicoes(refeicao);
        pedido.calcularValorTotal();

        return pedidoRepository.save(pedido);
    }

    @Transactional
    public Pedido updateBebida(Long pedidoId, List<Bebida> bebida) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        pedido.setBebidas(bebida);
        pedido.calcularValorTotal();

        return pedidoRepository.save(pedido);
    }


}
