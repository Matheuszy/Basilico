package com.Codexsystem.Basilico.Basilico.ordering.controller;

import com.Codexsystem.Basilico.Basilico.ordering.dto.PedidoRequestDto;
import com.Codexsystem.Basilico.Basilico.ordering.dto.PedidoResponseDto;
import com.Codexsystem.Basilico.Basilico.ordering.model.Pedido;
import com.Codexsystem.Basilico.Basilico.ordering.repository.PedidoRepository;import com.Codexsystem.Basilico.Basilico.ordering.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping
    public List<Pedido> buscarPedidosPorCliente(Integer clienteId) {
        return pedidoService.buscarPedidosPorCliente(clienteId);
    }

    @GetMapping("/{id}")
    public Pedido buscarPedidoPorId(@PathVariable Long pedidoId) {
        return pedidoService.buscarPedidoPorId(pedidoId);
    }

    @PostMapping("/create/order")
    public PedidoResponseDto createPedido(@RequestBody PedidoRequestDto dto) {
        Pedido pedidoSalvo = pedidoService.criarPedido(
                dto.clienteId(),
                dto.bebidas(),
                dto.refeicoes()
        );

        return new PedidoResponseDto(
                pedidoSalvo.getRefeicoes(),
                pedidoSalvo.getBebidas(),
                pedidoSalvo.getValorTotal()
        );
    }

    @DeleteMapping("/delete/order/{id}")
    public void deletePedido(@PathVariable("id") Long pedidoId) {
            pedidoService.cancelarPedido(pedidoId);


    }
}
