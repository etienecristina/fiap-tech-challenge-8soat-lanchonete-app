package br.com.fiap.tech_challenge.core.application.exception.produto;

public class ErroAoCadastrarProdutoException extends RuntimeException {
    public ErroAoCadastrarProdutoException(String message) {
        super(message);
    }
}