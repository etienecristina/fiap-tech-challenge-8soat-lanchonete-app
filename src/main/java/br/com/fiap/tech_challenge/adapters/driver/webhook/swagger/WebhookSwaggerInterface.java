package br.com.fiap.tech_challenge.adapters.driver.webhook.swagger;

import br.com.fiap.tech_challenge.adapters.driver.webhook.model.request.WebhookRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Tag(name = "Webhook", description = "API utilizada para receber as notificações do webhook")
public interface WebhookSwaggerInterface {

    @Operation(description = "Realiza o cadastro do cliente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pagamento realizado com sucesso!"),
        @ApiResponse(responseCode = "500", description = "Pagamento recusado.")
    })
    @RequestMapping(
        produces = "application/text",
        method = RequestMethod.POST,
        value = "/notification"
    )
    ResponseEntity<String> notificationReceiver(@RequestBody WebhookRequestDTO requestDTO);

}
