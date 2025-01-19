package br.com.fiap.tech_challenge.core.domain.model;

import lombok.Data;

@Data
public class Endereco {

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;

    public String getCep() {
        return cep.replace("-", "");
    }

}
