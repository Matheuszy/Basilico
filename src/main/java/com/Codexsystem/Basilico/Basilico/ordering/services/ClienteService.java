package com.Codexsystem.Basilico.Basilico.ordering.services;

import com.Codexsystem.Basilico.Basilico.ordering.enums.StatusCliente;
import com.Codexsystem.Basilico.Basilico.ordering.model.Cliente;
import com.Codexsystem.Basilico.Basilico.ordering.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService  {
    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente criarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> obterClientes() {
        return clienteRepository.findAll();
    }

    public void deletarCliente(Integer id) {
        var cliente = clienteRepository.findById(id);

        if (cliente.isPresent()) {
            cliente.get().setStatus(StatusCliente.INATIVO);
            clienteRepository.save(cliente.get());
        } else {

            throw new RuntimeException("Cliente não encontrado");
        }
    }

}
