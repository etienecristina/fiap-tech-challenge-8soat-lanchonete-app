package br.com.fiap.tech_challenge.adapters.driver.controller.swagger;

import br.com.fiap.tech_challenge.adapters.driver.controller.model.request.AtualizarClienteDTO;
import br.com.fiap.tech_challenge.adapters.driver.controller.model.request.CadastrarClienteDTO;
import br.com.fiap.tech_challenge.core.domain.model.Cliente;
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

@Tag(name = "Cliente", description = "Serviços relacionados às transações cadastrais do cliente")
public interface ClienteSwaggerInterface {

    @Operation(description = "Realiza o cadastro do cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente cadastrado com sucesso!", content =
                @Content(examples = @ExampleObject(
                        value = "Cliente cadastrado com sucesso!"))),
            @ApiResponse(responseCode = "400", description = "Cliente já cadastrado.", content =
                @Content(examples = @ExampleObject(
                        value = "Este cliente já foi cadastrado."))),
            @ApiResponse(responseCode = "500", description = "Erro ao cadastrar o cliente.", content =
                @Content(examples = @ExampleObject(
                        value = "Ocorreu um erro inesperado na hora de cadastrar o cliente.")))
    })
    @RequestMapping(
            produces = "application/text",
            method = RequestMethod.POST)
    ResponseEntity<String> cadastrarCliente(@RequestBody CadastrarClienteDTO cadastrar);

    @Operation(description = "Consulta cliente pelo número do documento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado", content =
                @Content(mediaType = "application/json", schema =
                @Schema(implementation = Cliente.class))),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado", content =
                @Content(mediaType = "application/text", examples =
                @ExampleObject(value = "Não foi encontrado nenhum resultado para essa pesquisa.")))
    })
    @RequestMapping(
            value = "/{cpf}",
            method = RequestMethod.GET)
    ResponseEntity<Cliente> buscarClientePorCPF(@Parameter(in = ParameterIn.PATH, description = "Número do documento do cliente", required = true, schema = @Schema()) @PathVariable String cpf);

    @Operation(description = "Atualiza informações do cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Cliente atualizado com sucesso", content =
                @Content(mediaType = "application/text")),
            @ApiResponse(responseCode = "404", description = "Não foi encontrado nenhum resultado para essa pesquisa.", content =
                @Content(mediaType = "application/text")),
            @ApiResponse(responseCode = "500", description = "Erro ao atualizar as informações do cliente.", content =
                @Content(mediaType = "application/text"))
    })
    @RequestMapping(
            method = RequestMethod.PUT)
    ResponseEntity<String> atualizarCliente(@RequestBody AtualizarClienteDTO atualizar);

    @Operation(description = "Exclui as informações do cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente excluído com sucesso!", content =
                @Content(mediaType = "application/text", examples =
                @ExampleObject(value = "Cliente excluído com sucesso!"))),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado.", content =
                @Content(mediaType = "application/text", examples =
                @ExampleObject(value = "Não foi encontrado nenhum resultado para essa pesquisa."))),
            @ApiResponse(responseCode = "500", description = "Erro ao atualizar as informações do cliente.", content =
                @Content(mediaType = "application/text", examples =
                @ExampleObject(value = "Ocorreu um erro inesperado durante a exclusão do cliente.")))
    })
    @RequestMapping(
            value = "/id/{id}",
            method = RequestMethod.DELETE)
    ResponseEntity<String> excluirCliente(@Parameter(in = ParameterIn.PATH, description = "Id do cliente", required = true, schema = @Schema()) @PathVariable Long id);

}
