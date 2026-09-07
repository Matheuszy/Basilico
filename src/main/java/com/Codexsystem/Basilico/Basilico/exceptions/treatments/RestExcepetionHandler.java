package com.Codexsystem.Basilico.Basilico.exceptions.treatments;

import com.Codexsystem.Basilico.Basilico.exceptions.ordering.ListarPedidosClienteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExcepetionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ListarPedidosClienteException.class)
    public ResponseEntity<String> handleListarPedidosClienteException(ListarPedidosClienteException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}