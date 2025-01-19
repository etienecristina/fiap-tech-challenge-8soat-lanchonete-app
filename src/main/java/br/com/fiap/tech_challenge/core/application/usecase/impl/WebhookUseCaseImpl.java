package br.com.fiap.tech_challenge.core.application.usecase.impl;

import br.com.fiap.tech_challenge.adapters.driver.controller.model.enums.SituacaoPedidoDTO;
import br.com.fiap.tech_challenge.core.application.exception.pedido.NenhumPedidoEncontradoException;
import br.com.fiap.tech_challenge.core.application.ports.gateway.PedidoGatewayPort;
import br.com.fiap.tech_challenge.core.application.usecase.WebhookUseCase;
import br.com.fiap.tech_challenge.core.domain.model.Pedido;
import org.springframework.stereotype.Service;

import static br.com.fiap.tech_challenge.core.application.constant.PedidoExceptionConstante.NENHUM_PEDIDO_FOI_ENCONTRADO_EXCEPTION;

@Service
public class WebhookUseCaseImpl implements WebhookUseCase {

  private final PedidoGatewayPort pedidoGatewayPort;

  public WebhookUseCaseImpl(PedidoGatewayPort pedidoGatewayPort) {
    this.pedidoGatewayPort = pedidoGatewayPort;
  }

  @Override
  public void atualizarStatusPedido(String id, SituacaoPedidoDTO situacao) {
    Pedido pedido = pedidoGatewayPort.consultaStatusPedidoPorMercadoPagoId(Long.valueOf(id));
    if(pedido == null) {
      throw new NenhumPedidoEncontradoException(NENHUM_PEDIDO_FOI_ENCONTRADO_EXCEPTION);
    }

    pedidoGatewayPort.atualizaStatusPedido(Long.valueOf(id), situacao);
  }

}
