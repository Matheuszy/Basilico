package com.Codexsystem.Basilico.Basilico.usuario.services;

import com.Codexsystem.Basilico.Basilico.usuario.model.Cliente;
import com.Codexsystem.Basilico.Basilico.usuario.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente criarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente obterClientePorId(Integer id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public List<Cliente> obterClientes() {
        return clienteRepository.findAll();
    }

    public void deletarCliente(Integer id) {
        clienteRepository.deleteById(id);
    }



}
