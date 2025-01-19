package br.com.fiap.tech_challenge.adapters.driver.controller.model.request;

import br.com.fiap.tech_challenge.adapters.driver.controller.model.enums.SituacaoPedidoDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class StatusPedidoRequestDTO {

  @Schema(name = "situacaoPedido", description = "Status do pedido na requisição")
  private SituacaoPedidoDTO situacaoPedido;

}
