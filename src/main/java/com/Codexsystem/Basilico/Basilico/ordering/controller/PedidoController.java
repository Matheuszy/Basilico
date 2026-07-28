package com.Codexsystem.Basilico.Basilico.ordering.controller;

import com.Codexsystem.Basilico.Basilico.ordering.dto.request.PedidoRequestDto;
import com.Codexsystem.Basilico.Basilico.ordering.dto.response.PedidoResponseDto;
import com.Codexsystem.Basilico.Basilico.ordering.model.Pedido;
import com.Codexsystem.Basilico.Basilico.ordering.repository.PedidoRepository;
import com.Codexsystem.Basilico.Basilico.ordering.services.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ADMIN') or @pedidoSecurity.isPedidoOwner(#pedidoId)")
    public List<Pedido> listarPedidosPorCliente(@RequestParam Integer clienteId) {
        return pedidoService.listarPedidosDoCliente(clienteId);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @pedidoSecurity.isPedidoOwner(#pedidoId)")
    public Pedido buscarPedidoPorId(@PathVariable("id") Long pedidoId, @RequestParam Integer clienteId) {
        return pedidoService.buscarPedidoPorId(pedidoId);
    }

    @PostMapping("/create/order")
    @PreAuthorize("hasRole('USER')")
    public PedidoResponseDto createPedido(@RequestBody @Valid PedidoRequestDto dto) {
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
    @PreAuthorize("hasRole('ADMIN') or @pedidoSecurity.isPedidoOwner(#pedidoId)")
    public void deletePedido(@PathVariable("id") Long pedidoId) {
            pedidoService.cancelarPedido(pedidoId);

    }
}
