package br.com.fiap.tech_challenge.adapters.driven.infrastructure.gateway;

import br.com.fiap.tech_challenge.adapters.driven.infrastructure.webhook.WebhookPagamento;
import br.com.fiap.tech_challenge.core.application.ports.gateway.WebhookGatewayPort;
import br.com.fiap.tech_challenge.adapters.driven.infrastructure.webhook.entity.PagamentoDTO;
import br.com.fiap.tech_challenge.core.domain.model.Cliente;
import br.com.fiap.tech_challenge.core.domain.model.Pedido;
import com.mercadopago.resources.payment.Payment;
import org.springframework.stereotype.Component;

@Component
public class WebhookGateway implements WebhookGatewayPort {

  private final WebhookPagamento webhookPagamento;

  public WebhookGateway(WebhookPagamento webhookPagamento) {
    this.webhookPagamento = webhookPagamento;
  }

  @Override
  public Payment processarPagamentoWebhookMP(Pedido pedido, Cliente cliente) {
    PagamentoDTO pagamentoDTO = new PagamentoDTO();

    pagamentoDTO.setIdPedido(pedido.getId());
    pagamentoDTO.setValor(pedido.getValorTotalPedido());
    pagamentoDTO.setIdCliente(cliente.getId());
    pagamentoDTO.setPrimeiroNome(cliente.getPrimeiroNome());
    pagamentoDTO.setSobrenome(cliente.getSobrenome());
    pagamentoDTO.setEmailCliente(cliente.getEmail());
    pagamentoDTO.setCpf(cliente.getCpf());

    return webhookPagamento.processarPagamento(pagamentoDTO);
  }

}
