package br.com.fiap.tech_challenge.adapters.driver.webhook.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WebhookRequestDTO {

  @Schema(name = "id", description = "Número de identificação do pagamento", example = "1319863112")
  private Long id;

  @Schema(name = "liveMode", description = "Indica se o evento ocorreu no ambiente de produção (true) ou sandbox (false)", example = "true")
  private Boolean liveMode;

  @Schema(name = "type", description = "Tipo de evento disparado pelo Mercado Pago", example = "payment.created")
  private String type;

  @Schema(name = "dateCreated", description = "Data e hora da criação do evento no formato ISO 8601", example = "2024-10-13T15:30:00Z")
  private String dateCreated;

  @Schema(name = "userId", description = "ID do usuário associado ao evento", example = "1")
  private Long userId;

  @Schema(name = "apiVersion", description = "Versão da API do Mercado Pago utilizada no evento", example = "v1")
  private String apiVersion;

  @Schema(name = "action", description = "Ação realizada relacionada ao evento, como 'payment.created' ou 'subscription.cancelled'", example = "payment.created")
  private String action;

  @Schema(name = "data", description = "Dados específicos do recurso relacionado ao evento")
  private DataDTO data;

}
