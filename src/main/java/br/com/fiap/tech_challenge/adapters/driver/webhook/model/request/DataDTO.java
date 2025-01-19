package br.com.fiap.tech_challenge.adapters.driver.webhook.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DataDTO {

  @Schema(name = "id", description = "ID do recurso associado ao evento, como um pagamento ou assinatura", example = "987654321")
  private Long id;

}