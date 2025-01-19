package br.com.fiap.tech_challenge.adapters.driver.controller.model.response;

import br.com.fiap.tech_challenge.adapters.driver.controller.model.enums.SituacaoPedidoDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class StatusPedidoReponseDTO {

  @Schema(name = "id", description = "Identificador único do pedido", example = "000001")
  private Long idPedido;

  @Schema(name = "situacaoPedido", description = "Status do pedido no response")
  private SituacaoPedidoDTO situacaoPedidoDTO;

}
