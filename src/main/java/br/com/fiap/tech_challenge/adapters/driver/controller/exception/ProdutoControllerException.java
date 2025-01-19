package br.com.fiap.tech_challenge.adapters.driver.controller.exception;

import br.com.fiap.tech_challenge.core.application.exception.produto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProdutoControllerException {

  private static final Logger LOGGER = LoggerFactory.getLogger(ProdutoControllerException.class);

  @ExceptionHandler(ProdutoJaCadastradoException.class)
  public ResponseEntity<String> produtoJaCadastradoException(ProdutoJaCadastradoException produtoJaCadastradoException) {
    LOGGER.error(produtoJaCadastradoException.getMessage());
    return new ResponseEntity<>(produtoJaCadastradoException.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ErroAoCadastrarProdutoException.class)
  public ResponseEntity<String> erroAoCadastrarProdutoException(ErroAoCadastrarProdutoException erroAoCadastrarProdutoException) {
    LOGGER.error(erroAoCadastrarProdutoException.getMessage());
    return new ResponseEntity<>(erroAoCadastrarProdutoException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(NenhumProdutoEncontradoException.class)
  public ResponseEntity<String> nenhumProdutoEncontradoException(NenhumProdutoEncontradoException nenhumProdutoEncontradoException) {
    LOGGER.error(nenhumProdutoEncontradoException.getMessage());
    return new ResponseEntity<>(nenhumProdutoEncontradoException.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(ProdutoNaoEncontradoException.class)
  public ResponseEntity<String> produtoNaoEncontradoException(ProdutoNaoEncontradoException produtoNaoEncontradoException) {
    LOGGER.error(produtoNaoEncontradoException.getMessage());
    return new ResponseEntity<>(produtoNaoEncontradoException.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(ErroAoAtualizarProdutoException.class)
  public ResponseEntity<String> erroAoAtualizarProdutoException(ErroAoAtualizarProdutoException erroAoAtualizarProdutoException) {
    LOGGER.error(erroAoAtualizarProdutoException.getMessage());
    return new ResponseEntity<>(erroAoAtualizarProdutoException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(ErroAoExcluirProdutoException.class)
  public ResponseEntity<String> erroAoExcluirProdutoException(ErroAoExcluirProdutoException erroAoExcluirProdutoException) {
    LOGGER.error(erroAoExcluirProdutoException.getMessage());
    return new ResponseEntity<>(erroAoExcluirProdutoException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
