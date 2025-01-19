package br.com.fiap.tech_challenge.core.application.ports.gateway;

import br.com.fiap.tech_challenge.adapters.driver.controller.model.enums.SituacaoPedidoDTO;
import br.com.fiap.tech_challenge.adapters.driver.controller.model.response.StatusPedidoReponseDTO;
import br.com.fiap.tech_challenge.core.domain.model.Cliente;
import br.com.fiap.tech_challenge.core.domain.model.Pedido;

import java.util.List;

public interface PedidoGatewayPort {
  Long cadastrarPedidos(Pedido pedido, Cliente cliente);
  List<Pedido> listaPedidos();
  Pedido consultaStatusPedidoPorId(Long id);
  StatusPedidoReponseDTO atualizaStatusPedido(Long id, SituacaoPedidoDTO situacaoPedido);
  Pedido consultaStatusPedidoPorMercadoPagoId(Long mercadoPagoId);

}
