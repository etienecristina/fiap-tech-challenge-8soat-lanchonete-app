package br.com.fiap.tech_challenge.core.application.exception.produto;

public class ProdutoJaCadastradoException extends RuntimeException{
    public ProdutoJaCadastradoException(String message) {
        super(message);
    }
}
