package com.Codexsystem.Basilico.Basilico.ordering.services;

import com.Codexsystem.Basilico.Basilico.ordering.model.Cliente;
import com.Codexsystem.Basilico.Basilico.ordering.model.Endereco;
import com.Codexsystem.Basilico.Basilico.ordering.repository.ClienteRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

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
    class CriarCliente {

        @Test
        @DisplayName("Deve criar um cliente com sucesso")
        void deveCriarClienteComSucesso() {

            Endereco endereco = new Endereco(
                    "Rua b",
                    "1234",
                    "casa 34",
                    "Sítio",
                    "São Paulo",
                    "SP",
                    "01000-000"
            );

            Cliente clienteInput =
                    new Cliente("Joao",
                            "joaozinho@gmail.com",
                            "1398888",
                            "11111111",
                            "senha2",
                            endereco);

            Cliente clienteEsperado =
                    new Cliente("Joao",
                            "joaozinho@gmail.com",
                            "1398888",
                            "11111111",
                            "senha2",
                            endereco);
            clienteEsperado.setId(1);

            doReturn(clienteEsperado)
                    .when(clienteRepository)
                    .save(any(Cliente.class));

            var output = clienteService.criarCliente(clienteInput);

            assertAll(
                    () -> assertNotNull(output),
                    () -> assertEquals(1, output.getId()),
                    () -> assertEquals("Joao", output.getNome()),
                    () -> assertEquals("joaozinho@gmail.com", output.getEmail()),
                    () -> assertTrue(output.getAtivo())
            );

            verify(clienteRepository, times(1))
                    .save(any(Cliente.class));
        }
    }

    @Nested
    class ObterClientes {

        @Test
        @DisplayName("Deve listar todos os clientes com sucesso")
        void deveListarClientesComSucesso() {

            Endereco endereco = new Endereco(
                    "Rua A",
                    "123",
                    "casa 3",
                    "Centro",
                    "São Paulo",
                    "SP",
                    "01000-000"
            );

            Cliente cliente1 =
                    new Cliente(
                            "Joao",
                            "joao@gmail.com",
                            "1111",
                            "12345678900",
                            "senha",
                            endereco
                    );
            cliente1.setId(1);

            Cliente cliente2 =
                    new Cliente("Maria",
                            "maria@gmail.com",
                            "2222", "12345678900",
                            "senha", endereco);
            cliente2.setId(2);

            var listaEsperada = List.of(cliente1, cliente2);

            when(clienteRepository.findAll())
                    .thenReturn(listaEsperada);

            var output = clienteService.obterClientes();

            assertAll(
                    () -> assertNotNull(output),
                    () -> assertEquals(2, output.size()),
                    () -> assertEquals("Joao", output.get(0).getNome()),
                    () -> assertEquals("Maria", output.get(1).getNome())
            );

            verify(clienteRepository, times(1)).findAll();
        }
    }

    @Nested
    class DeletarCliente {

        @Test
        @DisplayName("Deve deletar um cliente com sucesso")
        void deveDeletarClienteComSucesso() {

            clienteService.deletarCliente(1);

            verify(clienteRepository, times(1))
                    .deleteById(1);
        }
    }
}
