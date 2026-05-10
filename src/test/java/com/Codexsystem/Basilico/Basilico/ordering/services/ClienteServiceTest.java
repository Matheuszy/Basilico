package com.Codexsystem.Basilico.Basilico.ordering.services;

import com.Codexsystem.Basilico.Basilico.ordering.model.Cliente;
import com.Codexsystem.Basilico.Basilico.ordering.repository.ClienteRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @Nested
    class criarCliente {

        @Test
        @DisplayName("Deve criar um cliente com sucesso")
        void deveCriarClienteComSucesso() {
            Cliente clienteInput = new Cliente("Joao", "joaozinho@gmail.com", "1398888", "alolou");
            Cliente clienteEsperado = new Cliente("Joao", "joaozinho@gmail.com", "1398888", "alolou");
            clienteEsperado.setId(1);


            doReturn(clienteEsperado).when(clienteRepository).save(any(Cliente.class));


            var output = clienteService.criarCliente(clienteInput);


            assertAll(
                    () -> assertNotNull(output, "Output não deve ser null"),
                    () -> assertEquals(1, output.getId(), "ID deve ser 1"),
                    () -> assertEquals("Joao", output.getNome(), "Nome deve ser Joao"),
                    () -> assertEquals("joaozinho@gmail.com", output.getEmail(), "Email deve corresponder"),
                    () -> verify(clienteRepository, times(1)).save(any(Cliente.class)),
                    () -> assertTrue(output.getAtivo(), "Cliente deve estar ativo por padrão")
            );
        }

    }

}
