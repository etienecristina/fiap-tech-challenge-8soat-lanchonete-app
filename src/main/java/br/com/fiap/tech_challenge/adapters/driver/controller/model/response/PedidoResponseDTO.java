package br.com.fiap.tech_challenge.adapters.driver.controller.model.response;

import br.com.fiap.tech_challenge.adapters.driver.controller.model.enums.SituacaoPedidoDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class PedidoResponseDTO {

  @Schema(name = "id", description = "Identificador único do pedido", example = "000001")
  private String id;

  @Schema(name = "dataPedido", description = "Data em que o pedido foi atualizado")
  private Date dataPedido;

  @Schema(name = "valorTotalPedido", description = "Valor total do pedido", example = "100.22")
  private BigDecimal valorTotalPedido;

  @Schema(name = "situacaoPedido", description = "Status do pedido")
  private SituacaoPedidoDTO situacaoPedido;

  @Schema(name = "itens", description = "Lista de itens do pedido")
  private List<ItemPedidoResponseDTO> itens = new ArrayList<>();

}
