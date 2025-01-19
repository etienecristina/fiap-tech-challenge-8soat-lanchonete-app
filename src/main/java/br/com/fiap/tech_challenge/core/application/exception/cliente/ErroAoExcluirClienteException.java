package br.com.fiap.tech_challenge.core.application.exception.cliente;

public class ErroAoExcluirClienteException extends RuntimeException{
    public ErroAoExcluirClienteException(String message) {
        super(message);
    }
}
