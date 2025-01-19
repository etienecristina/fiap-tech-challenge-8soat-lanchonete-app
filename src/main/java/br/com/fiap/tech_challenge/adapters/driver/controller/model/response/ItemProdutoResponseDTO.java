package br.com.fiap.tech_challenge.adapters.driver.controller.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ItemProdutoResponseDTO {

  @Schema(name = "produtoId", description = "Id do Produto", example = "1L")
  private Long produtoId;

  @Schema(name = "descricao", description = "Descrição do Produto", example = "Lanche Natural")
  private String descricao;

}
