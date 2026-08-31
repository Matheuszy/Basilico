package com.Codexsystem.Basilico.Basilico.ordering.services;

import com.Codexsystem.Basilico.Basilico.catalog.model.Bebida;
import com.Codexsystem.Basilico.Basilico.catalog.model.Refeicao;
import com.Codexsystem.Basilico.Basilico.ordering.model.Cliente;
import com.Codexsystem.Basilico.Basilico.ordering.model.Endereco;
import com.Codexsystem.Basilico.Basilico.ordering.model.Pedido;
import com.Codexsystem.Basilico.Basilico.ordering.repository.ClienteRepository;
import com.Codexsystem.Basilico.Basilico.ordering.repository.PedidoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PedidoServiceTest {

    @Mock
    private ClienteRepository clienteRepository;
    @InjectMocks
    private PedidoService pedidoService;
    @Mock
    private PedidoRepository pedidoRepository;



    @Test
    public void deveCriarPedido(){

        Endereco endereco = new Endereco(
                "Rua b",
                "1234",
                "casa 34",
                "Sítio",
                "São Paulo",
                "SP",
                "01000-000"
        );

        Cliente cliente = new Cliente("Joao",
                "joaozinho@gmail.com",
                "1398888",
                "11111111",
                "senha2",
                endereco);

        Integer clienteId = 1;

        List<Bebida> bebidas = new ArrayList<>();
        List<Refeicao> refeicao = new ArrayList<>();
        Bebida bebida = new Bebida("coca", "cocazero", new BigDecimal("5.00"));
        Refeicao refeicao1 = new Refeicao("lasanha", "lasanha de frango", new BigDecimal("20.00"));
        refeicao.add(refeicao1);
        bebidas.add(bebida);


        Mockito.when(clienteRepository.findById(clienteId)).thenReturn(java.util.Optional.of(cliente));
        Mockito.when(pedidoRepository.save(Mockito.any(Pedido.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));


        Pedido pedidoResultado = pedidoService.criarPedido(clienteId, bebidas, refeicao);

        assertAll(
                () -> assertNotNull(pedidoResultado),
                () -> assertEquals(1, cliente.getPedido().size()), // Agora deve ser 1!
                () -> assertEquals(new BigDecimal("25.00"), pedidoResultado.getValorTotal())
        );

        Mockito.verify(pedidoRepository).save(Mockito.any(Pedido.class));








    }
}
