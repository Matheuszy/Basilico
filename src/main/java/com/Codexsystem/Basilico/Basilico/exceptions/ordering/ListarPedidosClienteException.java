package com.Codexsystem.Basilico.Basilico.exceptions.ordering;

public class ListarPedidosClienteException extends RuntimeException {

    public ListarPedidosClienteException(){
        super("Nenhum pedido encontrado para o cliente");
    }

    public ListarPedidosClienteException(String message) {
        super(message);
    }

}
