package br.com.fiap.tech_challenge.adapters.driver.controller.exception;

import br.com.fiap.tech_challenge.core.application.exception.pedido.ErroAoCadastrarPedidoException;
import br.com.fiap.tech_challenge.core.application.exception.pedido.NenhumPedidoEncontradoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PedidoControllerExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(PedidoControllerExceptionHandler.class);

    @ExceptionHandler(ErroAoCadastrarPedidoException.class)
    public ResponseEntity<String> erroAoCadastrarPedidoException(ErroAoCadastrarPedidoException erroAoCadastrarPedidoException) {
        LOGGER.error(erroAoCadastrarPedidoException.getMessage());
        return new ResponseEntity<>(erroAoCadastrarPedidoException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NenhumPedidoEncontradoException.class)
    public ResponseEntity<String> nenhumPedidoEncontradoException(NenhumPedidoEncontradoException nenhumPedidoEncontradoException) {
        LOGGER.error(nenhumPedidoEncontradoException.getMessage());
        return new ResponseEntity<>(nenhumPedidoEncontradoException.getMessage(), HttpStatus.NOT_FOUND);
    }

}
