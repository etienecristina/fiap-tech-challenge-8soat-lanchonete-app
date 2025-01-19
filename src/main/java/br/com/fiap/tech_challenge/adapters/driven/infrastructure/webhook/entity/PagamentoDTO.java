package br.com.fiap.tech_challenge.adapters.driven.infrastructure.webhook.entity;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class PagamentoDTO {
    private String idPedido;
    private BigDecimal valor;
    private Long idCliente;
    private String primeiroNome;
    private String sobrenome;
    private String emailCliente;
    private String cpf;

}