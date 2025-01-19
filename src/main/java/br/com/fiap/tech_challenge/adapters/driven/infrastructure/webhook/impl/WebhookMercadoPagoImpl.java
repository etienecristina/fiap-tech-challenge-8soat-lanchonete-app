package br.com.fiap.tech_challenge.adapters.driven.infrastructure.webhook.impl;

import br.com.fiap.tech_challenge.adapters.driven.infrastructure.webhook.WebhookPagamento;
import br.com.fiap.tech_challenge.adapters.driven.infrastructure.webhook.entity.PagamentoDTO;
import com.google.gson.Gson;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WebhookMercadoPagoImpl implements WebhookPagamento {

  @Value("${webhook.mercado-pago.access-token}")
  private String accessToken;
  //@Value("${tech-challenge.host}")
  private String hostNgrok;

  private final static Logger LOGGER = LoggerFactory.getLogger(WebhookMercadoPagoImpl.class);

  @Override
  public Payment processarPagamento(PagamentoDTO pagamentoDTO) {
    MercadoPagoConfig.setAccessToken(accessToken);

    PaymentClient client = new PaymentClient();

//    String endpointWebhook = "api/v1/webhook/notification";

    PaymentCreateRequest createRequest =
        PaymentCreateRequest.builder()
            .transactionAmount(pagamentoDTO.getValor())
            .description("Loja Tech Challenge")
            .installments(1)
            .paymentMethodId("pix")
            .binaryMode(true)
            .payer(
                PaymentPayerRequest.builder()
                    .email(pagamentoDTO.getEmailCliente())
                    .firstName(pagamentoDTO.getPrimeiroNome())
                    .lastName(pagamentoDTO.getSobrenome())
                    .build())
//            .notificationUrl(hostNgrok + endpointWebhook)
            .build();

    Payment payment = new Payment();
    try {
      payment = client.create(createRequest);
      LOGGER.info("Mercado Pago: Pedido criado com sucesso! {}", payment);
    } catch (MPApiException ex) {
      LOGGER.error(String.format("MercadoPago Error. Status: %s, Content: %s%n",
          ex.getApiResponse().getStatusCode(), ex.getApiResponse().getContent()));
    } catch (MPException ex) {
      LOGGER.error(ex.getMessage());
    }

    LOGGER.info("Mercado Pago: Qr Code: {}", payment.getPointOfInteraction().getTransactionData().getQrCode());
    LOGGER.info("Mercado Pago: Qr Code Base 64: {}", payment.getPointOfInteraction().getTransactionData().getQrCodeBase64());
    LOGGER.info("Mercado Pago: Página de acesso do QR Code: {}", payment.getPointOfInteraction().getTransactionData().getTicketUrl());

    return payment;
  }
}
