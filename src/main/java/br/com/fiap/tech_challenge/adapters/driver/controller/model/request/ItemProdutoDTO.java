package br.com.fiap.tech_challenge.adapters.driver.controller.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ItemProdutoDTO {

  @Schema(name = "produtoId", description = "Número de identificação do produto", example = "1L")
  private Long produtoId;

}
