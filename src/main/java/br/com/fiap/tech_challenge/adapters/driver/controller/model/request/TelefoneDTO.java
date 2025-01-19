package br.com.fiap.tech_challenge.adapters.driver.controller.model.request;

import br.com.fiap.tech_challenge.adapters.driver.controller.model.enums.TipoTelefone;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TelefoneDTO {

    @JsonProperty(required = true)
    @Schema(name = "tipoTelefone", description = "Tipo Telefone")
    private TipoTelefone tipoTelefone;

    @JsonProperty(required = true)
    @Schema(name = "ddd", description = "DDD", example = "11")
    private String ddd;

    @JsonProperty(required = true)
    @Schema(name = "numero", description = "Número de telefone", example = "912341234")
    private String numero;

}
