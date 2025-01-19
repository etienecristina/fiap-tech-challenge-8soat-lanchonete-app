package br.com.fiap.tech_challenge.core.application.usecase;

import br.com.fiap.tech_challenge.adapters.driver.controller.model.enums.SituacaoPedidoDTO;

public interface WebhookUseCase {
  void atualizarStatusPedido(String id, SituacaoPedidoDTO situacao);
}
