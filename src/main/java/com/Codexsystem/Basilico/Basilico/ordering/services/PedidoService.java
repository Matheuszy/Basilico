package com.Codexsystem.Basilico.Basilico.ordering.services;

import com.Codexsystem.Basilico.Basilico.catalog.model.Bebida;
import com.Codexsystem.Basilico.Basilico.ordering.enums.StatusPedido;
import com.Codexsystem.Basilico.Basilico.ordering.model.Pedido;
import com.Codexsystem.Basilico.Basilico.catalog.model.Refeicao;
import com.Codexsystem.Basilico.Basilico.ordering.repository.PedidoRepository;
import com.Codexsystem.Basilico.Basilico.ordering.model.Cliente;
import com.Codexsystem.Basilico.Basilico.ordering.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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

        bebida = Objects.requireNonNullElse(bebida, List.of());
        refeicao = Objects.requireNonNullElse(refeicao, List.of());

        if (bebida.isEmpty() && refeicao.isEmpty()) {
            throw new IllegalArgumentException(
                    "O pedido deve conter pelo menos uma bebida ou uma refeição.");
        }

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

    @Transactional
    public void cancelarPedido(Long pedidoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        if (pedido.getStatusPedido() == StatusPedido.ENTREGUE) {
            throw new RuntimeException("Pedido já está foi entregue");
        } else if (pedido.getStatusPedido() == StatusPedido.CANCELADO) {
            throw new RuntimeException("Pedido já está cancelado");
        } else {
            pedido.setStatusPedido(StatusPedido.CANCELADO);
            pedidoRepository.save(pedido);
        }
    }

    public Pedido buscarPedidoPorId(Long pedidoId) {
        return pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

    public Pedido buscarPedidoDoCliente(Long pedidoId, Integer clienteId) {
        return pedidoRepository.findPedidoCompletoPorIdECliente(pedidoId, clienteId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

    public List<Pedido> listarPedidosDoCliente(Integer clienteId) {
        if(pedidoRepository.findPedidosPorClienteId(clienteId).isEmpty()){
            throw new RuntimeException("Nenhum pedido encontrado para o cliente com ID: " + clienteId);
        }
        return pedidoRepository.findPedidosPorClienteId(clienteId);
    }
}
