package com.Codexsystem.Basilico.Basilico.usuario.controller;

import com.Codexsystem.Basilico.Basilico.cardapio.model.Pedido;
import com.Codexsystem.Basilico.Basilico.usuario.dto.ClienteRequestDto;
import com.Codexsystem.Basilico.Basilico.usuario.dto.ClienteResponseDto;
import com.Codexsystem.Basilico.Basilico.usuario.model.Cliente;
import com.Codexsystem.Basilico.Basilico.usuario.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> listar(){
        return clienteService.obterClientes();
    }

    @GetMapping("/{}")
    public ClienteResponseDto obterClientePorId(@RequestParam("id") Integer id){
        Cliente clienteId = clienteService.obterClientePorId(id);
        return new ClienteResponseDto(clienteId.getNome(), clienteId.getPedido());
    }

    @PostMapping("/newclient")
    public void criarCliente(@RequestBody ClienteRequestDto cliente){
        Cliente newCliente = new Cliente(cliente.nome(), cliente.email(), cliente.senha());
        ResponseEntity.status(200).body(clienteService.criarCliente(newCliente));
    }

    @DeleteMapping("/deleteclient")
    public void deletarCliente(@RequestParam("id") Integer id){
        clienteService.deletarCliente(id);
    }
}
