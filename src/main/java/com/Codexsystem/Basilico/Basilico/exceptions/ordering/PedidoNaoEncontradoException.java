package com.Codexsystem.Basilico.Basilico.exceptions.ordering;

public class PedidoNaoEncontradoException extends RuntimeException {

    public PedidoNaoEncontradoException() {
        super("Pedido não encontrado.");
    }

    public PedidoNaoEncontradoException(String message) {
        super(message);
    }
}
