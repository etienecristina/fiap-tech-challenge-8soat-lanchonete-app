package br.com.fiap.tech_challenge.core.domain.model;

import br.com.fiap.tech_challenge.core.domain.model.enums.TipoTelefone;
import lombok.Data;

@Data
public class Telefone {

    private TipoTelefone tipoTelefone;
    private String ddd;
    private String numero;

    public String getNumero() {
        return numero.replace("-", "");
    }

}
