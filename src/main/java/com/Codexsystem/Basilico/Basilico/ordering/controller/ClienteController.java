package com.Codexsystem.Basilico.Basilico.ordering.controller;

import com.Codexsystem.Basilico.Basilico.ordering.dto.ClienteRequestDto;
import com.Codexsystem.Basilico.Basilico.ordering.dto.ClienteResponseDto;
import com.Codexsystem.Basilico.Basilico.ordering.model.Cliente;
import com.Codexsystem.Basilico.Basilico.ordering.services.ClienteService;
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
