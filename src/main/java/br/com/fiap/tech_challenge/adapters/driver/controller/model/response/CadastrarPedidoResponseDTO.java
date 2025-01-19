package br.com.fiap.tech_challenge.adapters.driver.controller.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CadastrarPedidoResponseDTO {

  @Schema(name = "mensagem", description = "Mensagem de confirmação do cadastro", example = "1319863112")
  private String mensagem;

  @Schema(name = "idPedido", description = "Número de identificação do pedido", example = "1L")
  private Long idPedido;

  @Schema(name = "qrCode", description = "QR Code retornado pelo Mercado Pago")
  private String qrCode;

  @Schema(name = "qrCodeBase64", description = "QR Code em Base 64 retornado pelo Mercado Pago")
  private String qrCodeBase64;

  @Schema(name = "qrCodeUrl", description = "URL com os dados do pagamento + QR Code")
  private String qrCodeUrl;

}
