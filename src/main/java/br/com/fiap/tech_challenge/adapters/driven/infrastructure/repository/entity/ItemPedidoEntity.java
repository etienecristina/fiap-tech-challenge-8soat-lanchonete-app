package br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ITEM_PEDIDO")
public class ItemPedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private ProdutoEntity produto;

    @ManyToOne
    private PedidoEntity pedido;

    @Column(name = "valor_unitario", nullable = false)
    @DecimalMin(value = "0.01", message = "O valor unitário deve ser maior que zero")
    private BigDecimal valorUnitario;

    @Column(name = "quantidade", nullable = false)
    @DecimalMin(value = "1", message = "A quantidade deve ser maior que zero")
    private int quantidade;

    @Column(name = "valor_total_item", nullable = false)
    @DecimalMin(value = "0.01", message = "O valor total deve ser maior que zero")
    private BigDecimal valorTotalPedido;

}
