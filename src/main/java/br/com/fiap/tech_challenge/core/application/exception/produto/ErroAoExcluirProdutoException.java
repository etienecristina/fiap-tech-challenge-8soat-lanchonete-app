package br.com.fiap.tech_challenge.core.application.exception.produto;

public class ErroAoExcluirProdutoException extends RuntimeException {
    public ErroAoExcluirProdutoException(String message) {
        super(message);
    }
}