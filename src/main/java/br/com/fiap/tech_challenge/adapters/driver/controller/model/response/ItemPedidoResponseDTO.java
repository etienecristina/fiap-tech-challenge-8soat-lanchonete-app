package br.com.fiap.tech_challenge.adapters.driver.controller.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class ItemPedidoResponseDTO {

  private ItemProdutoResponseDTO produto;

  @Schema(name = "valorUnitario", description = "Valor do Produto", example = "15.99")
  private BigDecimal valorUnitario;

  @Schema(name = "quantidade", description = "Quantidade", example = "2")
  private int quantidade;

  @Schema(name = "valorTotalProduto", description = "Valor total do produto", example = "30.98")
  private BigDecimal valorTotalPedido;

}
