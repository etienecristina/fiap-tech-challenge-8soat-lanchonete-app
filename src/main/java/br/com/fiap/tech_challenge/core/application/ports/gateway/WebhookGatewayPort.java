package br.com.fiap.tech_challenge.core.application.ports.gateway;

import br.com.fiap.tech_challenge.core.domain.model.Cliente;
import br.com.fiap.tech_challenge.core.domain.model.Pedido;
import com.mercadopago.resources.payment.Payment;

public interface WebhookGatewayPort {

  Payment processarPagamentoWebhookMP(Pedido pedido, Cliente cliente);

}
