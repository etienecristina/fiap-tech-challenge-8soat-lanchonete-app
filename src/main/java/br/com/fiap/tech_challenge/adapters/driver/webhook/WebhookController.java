package br.com.fiap.tech_challenge.adapters.driver.webhook;

import br.com.fiap.tech_challenge.adapters.driven.infrastructure.gateway.PedidoGateway;
import br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.PedidoRepository;
import br.com.fiap.tech_challenge.adapters.driver.controller.model.enums.SituacaoPedidoDTO;
import br.com.fiap.tech_challenge.adapters.driver.webhook.model.request.WebhookRequestDTO;
import br.com.fiap.tech_challenge.adapters.driver.webhook.swagger.WebhookSwaggerInterface;
import br.com.fiap.tech_challenge.core.application.usecase.impl.WebhookUseCaseImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/webhook")
public class WebhookController implements WebhookSwaggerInterface {

  private final PedidoRepository pedidoRepository;

  public WebhookController(PedidoRepository pedidoRepository) {
    this.pedidoRepository = pedidoRepository;
  }

  @Override
  public ResponseEntity<String> notificationReceiver(WebhookRequestDTO requestDTO) {
    SituacaoPedidoDTO situacao = getSituacaoPedidoFromAction(requestDTO.getAction());

    if (situacao != null) {
      var pedidoGateway = new PedidoGateway(this.pedidoRepository);
      var webhookUseCase = new WebhookUseCaseImpl(pedidoGateway);

      webhookUseCase.atualizarStatusPedido(requestDTO.getId().toString(), situacao);
      return new ResponseEntity<>("Pagamento atualizado", HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Ação desconhecida", HttpStatus.CREATED);
    }
  }

  private SituacaoPedidoDTO getSituacaoPedidoFromAction(String action) {
      return switch (action) {
          case "payment.created" -> SituacaoPedidoDTO.AGUARDANDO_PAGAMENTO;
          case "payment.approved" -> SituacaoPedidoDTO.PAGAMENTO_RECEBIDO;
          case "payment.rejected" -> SituacaoPedidoDTO.FINALIZADO;
          default -> null;
      };
  }
}
