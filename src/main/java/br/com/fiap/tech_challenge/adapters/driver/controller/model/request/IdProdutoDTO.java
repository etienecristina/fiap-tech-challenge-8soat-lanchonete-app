package br.com.fiap.tech_challenge.adapters.driver.controller.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class IdProdutoDTO {

  @JsonProperty(required = true)
  @Schema(name = "produtoId", description = "Id do Produto", example = "1")
  @NotNull
  private Long produtoId;

}
