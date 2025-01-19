package br.com.fiap.tech_challenge.core.application.ports.gateway;

import br.com.fiap.tech_challenge.core.domain.model.Produto;
import br.com.fiap.tech_challenge.core.domain.model.enums.CategoriaProduto;

import java.util.List;

public interface ProdutoGatewayPort {
  void deleteById(Long id);
  Long save(Produto produto);
  Produto findById(Long produtoId);
  List<Produto> findProdutosByCategoria(CategoriaProduto categoriaProduto);

}
