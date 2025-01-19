package br.com.fiap.tech_challenge.adapters.driver.controller.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class EnderecoDTO {

    @JsonProperty(required = true)
    @Schema(name = "cep", description = "CEP", example = "12345-12")
    private String cep;

    @JsonProperty(required = true)
    @Schema(name = "logradouro", description = "Logradouro", example = "Rua exemplo, 123")
    private String logradouro;

    @Schema(name = "complemento", description = "Complemento", example = "Bloco A, Apto 123")
    private String complemento;

    @JsonProperty(required = true)
    @Schema(name = "bairro", description = "Bairro", example = "Vila Exemplo")
    private String bairro;

    @JsonProperty(required = true)
    @Schema(name = "cidade", description = "Cidade", example = "São Paulo")
    private String cidade;

    @JsonProperty(required = true)
    @Schema(name = "estado", description = "Estado", example = "SP")
    private String estado;

}
