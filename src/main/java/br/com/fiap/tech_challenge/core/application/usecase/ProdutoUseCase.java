package br.com.fiap.tech_challenge.core.application.usecase;

import br.com.fiap.tech_challenge.adapters.driver.controller.model.request.AtualizarProdutoDTO;
import br.com.fiap.tech_challenge.adapters.driver.controller.model.request.CadastrarProdutoDTO;
import br.com.fiap.tech_challenge.core.domain.model.Produto;
import br.com.fiap.tech_challenge.core.domain.model.enums.CategoriaProduto;

import java.util.List;

public interface ProdutoUseCase {

    Long cadastrarProduto(CadastrarProdutoDTO cadastrar);

    List<Produto> buscarProdutosPorCategoria(CategoriaProduto categoriaProduto);

    Long atualizarProduto(AtualizarProdutoDTO atualizar);

    void excluirProduto(Long produtoId);
}
