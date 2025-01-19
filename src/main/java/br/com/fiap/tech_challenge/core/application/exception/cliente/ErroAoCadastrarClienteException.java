package br.com.fiap.tech_challenge.core.application.exception.cliente;

public class ErroAoCadastrarClienteException extends RuntimeException {
    public ErroAoCadastrarClienteException(String message) {
        super(message);
    }
}
