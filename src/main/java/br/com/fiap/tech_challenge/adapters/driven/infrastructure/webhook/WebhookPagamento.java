package br.com.fiap.tech_challenge.adapters.driven.infrastructure.webhook;

import br.com.fiap.tech_challenge.adapters.driven.infrastructure.webhook.entity.PagamentoDTO;
import com.mercadopago.resources.payment.Payment;

public interface WebhookPagamento {

  Payment processarPagamento(PagamentoDTO pagamentoDTO);

}
