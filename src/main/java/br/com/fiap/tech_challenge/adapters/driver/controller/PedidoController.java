package br.com.fiap.tech_challenge.adapters.driver.controller;

import br.com.fiap.tech_challenge.adapters.driven.infrastructure.gateway.ClienteGateway;
import br.com.fiap.tech_challenge.adapters.driven.infrastructure.gateway.PedidoGateway;
import br.com.fiap.tech_challenge.adapters.driven.infrastructure.gateway.ProdutoGateway;
import br.com.fiap.tech_challenge.adapters.driven.infrastructure.gateway.WebhookGateway;
import br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.ClienteRepository;
import br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.PedidoRepository;
import br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.ProdutoRepository;
import br.com.fiap.tech_challenge.adapters.driven.infrastructure.webhook.WebhookPagamento;
import br.com.fiap.tech_challenge.adapters.driver.controller.model.request.CadastrarPedidoDTO;
import br.com.fiap.tech_challenge.adapters.driver.controller.model.request.StatusPedidoRequestDTO;
import br.com.fiap.tech_challenge.adapters.driver.controller.model.response.CadastrarPedidoResponseDTO;
import br.com.fiap.tech_challenge.adapters.driver.controller.model.response.PedidoResponseDTO;
import br.com.fiap.tech_challenge.adapters.driver.controller.model.response.PedidosResponseDTO;
import br.com.fiap.tech_challenge.adapters.driver.controller.model.response.StatusPedidoReponseDTO;
import br.com.fiap.tech_challenge.adapters.driver.controller.swagger.PedidoSwaggerInterface;
import br.com.fiap.tech_challenge.core.application.usecase.impl.PedidoUseCaseImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/pedido")
public class PedidoController implements PedidoSwaggerInterface {

    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final PedidoRepository pedidoRepository;
    private final WebhookPagamento webhookPagamento;

    public PedidoController(ClienteRepository clienteRepository,
                            ProdutoRepository produtoRepository,
                            PedidoRepository pedidoRepository,
                            WebhookPagamento webhookPagamento) {
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
        this.pedidoRepository = pedidoRepository;
        this.webhookPagamento = webhookPagamento;
    }

    @Override
    public ResponseEntity<CadastrarPedidoResponseDTO> cadastrarPedido(CadastrarPedidoDTO cadastrar) {
        var clienteGateway = new ClienteGateway(this.clienteRepository);
        var produtoGateway = new ProdutoGateway(this.produtoRepository);
        var pedidoGateway = new PedidoGateway(this.pedidoRepository);
        var webhookGateway = new WebhookGateway(this.webhookPagamento);

        var pedidoUseCase = new PedidoUseCaseImpl(
            clienteGateway, pedidoGateway, produtoGateway, webhookGateway);

        CadastrarPedidoResponseDTO responseDTO = pedidoUseCase.cadastrarPedido(cadastrar);
        responseDTO.setMensagem("Pedido cadastrado com sucesso.");
        return new ResponseEntity<>(responseDTO,
                HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> listarPedidos() {
        var clienteGateway = new ClienteGateway(this.clienteRepository);
        var produtoGateway = new ProdutoGateway(this.produtoRepository);
        var pedidoGateway = new PedidoGateway(this.pedidoRepository);
        var webhookGateway = new WebhookGateway(this.webhookPagamento);

        var pedidoUseCase = new PedidoUseCaseImpl(
            clienteGateway, pedidoGateway, produtoGateway, webhookGateway);

        PedidosResponseDTO pedidoDTOS = new PedidosResponseDTO();
        List<PedidoResponseDTO> pedidoResponseDTOList = pedidoUseCase.listarPedidos();
        pedidoDTOS.setPedidos(pedidoResponseDTOList);
        return new ResponseEntity<>(pedidoDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> consultaStatusPedido(Long id) {
        var clienteGateway = new ClienteGateway(this.clienteRepository);
        var produtoGateway = new ProdutoGateway(this.produtoRepository);
        var pedidoGateway = new PedidoGateway(this.pedidoRepository);
        var webhookGateway = new WebhookGateway(this.webhookPagamento);

        var pedidoUseCase = new PedidoUseCaseImpl(
            clienteGateway, pedidoGateway, produtoGateway, webhookGateway);

        StatusPedidoReponseDTO statusPedido = pedidoUseCase.consultaStatusPedido(id);
        return new ResponseEntity<>(statusPedido, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> atualizaStatusPedido(Long id, StatusPedidoRequestDTO request) {
        var clienteGateway = new ClienteGateway(this.clienteRepository);
        var produtoGateway = new ProdutoGateway(this.produtoRepository);
        var pedidoGateway = new PedidoGateway(this.pedidoRepository);
        var webhookGateway = new WebhookGateway(this.webhookPagamento);

        var pedidoUseCase = new PedidoUseCaseImpl(
            clienteGateway, pedidoGateway, produtoGateway, webhookGateway);

        StatusPedidoReponseDTO statusPedido = pedidoUseCase.atualizarStatusPedido(id, request.getSituacaoPedido());
        return new ResponseEntity<>(statusPedido, HttpStatus.ACCEPTED);
    }

}
