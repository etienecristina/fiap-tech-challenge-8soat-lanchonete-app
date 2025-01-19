package br.com.fiap.tech_challenge.core.application.exception.produto;

public class NenhumProdutoEncontradoException extends RuntimeException {
    public NenhumProdutoEncontradoException(String message) {
        super(message);
    }
}
