package br.com.fiap.tech_challenge.adapters.driver.controller.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class AtualizarClienteDTO {

    @Schema(name = "id", description = "Número de identificação do cliente", example = "1L")
    private Long id;

    @JsonProperty(required = true)
    @Schema(name = "cpf", description = "Número de documento", example = "12345678910")
    private String cpf;

    @JsonProperty(required = true)
    @Schema(name = "primeiroNome", description = "Primeiro nome", example = "Nome")
    private String primeiroNome;

    @JsonProperty(required = true)
    @Schema(name = "sobrenome", description = "Sobrenome", example = "Teste")
    private String sobrenome;

    @JsonProperty(required = true)
    @Schema(name = "email", description = "E-mail", example = "teste@exemplo.com")
    private String email;

    @Schema(name = "telefones", description = "Números de telefone")
    private List<TelefoneDTO> telefones;

    @Schema(name = "enderecos", description = "Endereços do cliente")
    private List<EnderecoDTO> enderecos;

}
