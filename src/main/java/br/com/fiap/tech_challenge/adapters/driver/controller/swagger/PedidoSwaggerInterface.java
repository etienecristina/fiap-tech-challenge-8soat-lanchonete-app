package br.com.fiap.tech_challenge.adapters.driver.controller.swagger;

import br.com.fiap.tech_challenge.adapters.driver.controller.model.request.CadastrarPedidoDTO;
import br.com.fiap.tech_challenge.adapters.driver.controller.model.request.StatusPedidoRequestDTO;
import br.com.fiap.tech_challenge.adapters.driver.controller.model.response.CadastrarPedidoResponseDTO;
import br.com.fiap.tech_challenge.adapters.driver.controller.model.response.PedidosResponseDTO;
import br.com.fiap.tech_challenge.adapters.driver.controller.model.response.StatusPedidoReponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Tag(name = "Pedido", description = "Serviços relacionados ao cadastro dos pedidos")
public interface PedidoSwaggerInterface {

    @Operation(description = "Realiza o cadastro do pedido do cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pedido cadastrado com sucesso. Código: 1", content =
                @Content(mediaType = "application/json", schema =
                @Schema(implementation = CadastrarPedidoResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "Erro ao cadastrar pedido", content =
                @Content(mediaType = "application/text", examples =
                @ExampleObject(value = "Ocorreu um erro inesperado ao cadastrar o pedido.")))
    })
    @RequestMapping(
            method = RequestMethod.POST)
    ResponseEntity<CadastrarPedidoResponseDTO> cadastrarPedido(@RequestBody CadastrarPedidoDTO cadastrar);

    @Operation(description = "Lista todos os pedidos em andamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista pedidos", content =
                @Content(mediaType = "application/json", schema =
                @Schema(implementation = PedidosResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Nenhum pedido foi encontrado", content =
                @Content(mediaType = "application/text", examples =
                @ExampleObject(value = "Nenhum pedido foi encontrado")))
    })
    @RequestMapping(
            value = "/listar",
            method = RequestMethod.GET)
    ResponseEntity<Object> listarPedidos();

  @Operation(description = "Retorna status do pedido")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Status do pedido", content =
      @Content(mediaType = "application/json", schema =
      @Schema(implementation = StatusPedidoReponseDTO.class))),
      @ApiResponse(responseCode = "404", description = "Nenhum pedido foi encontrado", content =
      @Content(mediaType = "application/text", examples =
      @ExampleObject(value = "Nenhum pedido foi encontrado")))
  })
  @RequestMapping(
      value = "/{id}/status",
      method = RequestMethod.GET)
  ResponseEntity<Object> consultaStatusPedido(@Parameter(in = ParameterIn.PATH, description = "Número de identificação do pedido", required = true, schema = @Schema()) @PathVariable Long id);

  @Operation(description = "Atualiza status do pedido")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "202", description = "Status do pedido atualizado", content =
      @Content(mediaType = "application/json", schema =
      @Schema(implementation = StatusPedidoReponseDTO.class))),
      @ApiResponse(responseCode = "404", description = "Nenhum pedido foi encontrado", content =
      @Content(mediaType = "application/text", examples =
      @ExampleObject(value = "Nenhum pedido foi encontrado"))),
      @ApiResponse(responseCode = "500", description = "Ocorreu um erro inesperado ao atualizar o pedido.", content =
      @Content(mediaType = "application/text", examples =
      @ExampleObject(value = "Nenhum pedido foi encontrado")))
  })
  @RequestMapping(
      value = "/{id}/status",
      method = RequestMethod.PUT)
  ResponseEntity<Object> atualizaStatusPedido(@PathVariable Long id, @RequestBody StatusPedidoRequestDTO request);


}
