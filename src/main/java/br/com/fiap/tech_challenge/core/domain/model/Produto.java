package br.com.fiap.tech_challenge.core.domain.model;

import br.com.fiap.tech_challenge.core.domain.model.enums.CategoriaProduto;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Produto {

    private Long produtoId;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private CategoriaProduto categoria;
}
