package br.com.fiap.tech_challenge.core.domain.model;

import br.com.fiap.tech_challenge.core.domain.model.enums.SituacaoPedido;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class Pedido {

    private String id;
    private Long clientId;
    private Date dataPedido;
    private BigDecimal valorTotalPedido;
    private SituacaoPedido situacaoPedido;
    private Long mercadoPagoId;
    private List<ItemPedido> itens;

    public Pedido(String id, Long clientId, Date dataPedido, BigDecimal valorTotalPedido, SituacaoPedido situacaoPedido, Long mercadoPagoId, List<ItemPedido> itens) {
        this.id = id;
        this.clientId = clientId;
        this.dataPedido = dataPedido;
        this.valorTotalPedido = valorTotalPedido;
        this.situacaoPedido = situacaoPedido;
        this.mercadoPagoId = mercadoPagoId;
        this.itens = itens;
    }
}
