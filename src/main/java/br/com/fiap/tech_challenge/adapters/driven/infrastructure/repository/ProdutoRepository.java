package br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository;

import br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.entity.ProdutoEntity;
import br.com.fiap.tech_challenge.core.domain.model.enums.CategoriaProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {
    List<ProdutoEntity> findAllByCategoriaProduto(CategoriaProduto categoriaProduto);
}
