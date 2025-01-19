package br.com.fiap.tech_challenge.adapters.driver.controller.mapper;

import br.com.fiap.tech_challenge.adapters.driver.controller.model.request.AtualizarProdutoDTO;
import br.com.fiap.tech_challenge.adapters.driver.controller.model.request.CadastrarProdutoDTO;
import br.com.fiap.tech_challenge.core.domain.model.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
    unmappedSourcePolicy = ReportingPolicy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ProdutoDTOMapper {
  Produto cadastrarDtoToProduto(CadastrarProdutoDTO cadastrar);
  Produto atualizarProdutoDtoToProduto(AtualizarProdutoDTO atualizar);

}
