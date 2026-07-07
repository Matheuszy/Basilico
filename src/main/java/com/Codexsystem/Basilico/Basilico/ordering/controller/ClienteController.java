package com.Codexsystem.Basilico.Basilico.ordering.controller;

import com.Codexsystem.Basilico.Basilico.ordering.dto.request.ClienteRequestDto;
import com.Codexsystem.Basilico.Basilico.ordering.model.Cliente;
import com.Codexsystem.Basilico.Basilico.ordering.model.Endereco;
import com.Codexsystem.Basilico.Basilico.ordering.services.ClienteService;
import jakarta.validation.Valid;
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
    public ResponseEntity<Cliente> criarCliente(@RequestBody @Valid ClienteRequestDto cliente) {

        Endereco endereco = new Endereco(
                cliente.endereco().rua(),
                cliente.endereco().numero(),
                cliente.endereco().complemento(),
                cliente.endereco().bairro(),
                cliente.endereco().cidade(),
                cliente.endereco().estado(),
                cliente.endereco().cep()
        );

        Cliente novoCliente = new Cliente(
                cliente.nome(),
                cliente.email(),
                cliente.telefone(),
                cliente.cpf(),
                cliente.senha(),
                endereco
        );

        Cliente clienteSalvo = clienteService.criarCliente(novoCliente);

        return ResponseEntity.status(201).body(clienteSalvo);
    }

    @DeleteMapping("/deleteclient")
    public void deletarCliente(@RequestParam("id") Integer id){
        clienteService.deletarCliente(id);
    }
}
