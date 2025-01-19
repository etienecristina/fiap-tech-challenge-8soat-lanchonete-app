package br.com.fiap.tech_challenge.core.application.mapper;

import br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.entity.ProdutoEntity;
import br.com.fiap.tech_challenge.core.domain.model.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ProdutoMapper {

    @Mapping(source = "entity.produtoId", target = "produtoId")
    @Mapping(source = "entity.categoriaProduto", target = "categoria")
    Produto toDTO(ProdutoEntity entity);

    @Mapping(source = "produto.produtoId", target = "produtoId")
    @Mapping(source = "produto.categoria", target = "categoriaProduto")
    ProdutoEntity toEntity(Produto produto);

}
