package br.com.fiap.tech_challenge.core.application.usecase.impl;

import br.com.fiap.tech_challenge.adapters.driver.controller.mapper.PedidoDTOMapperImpl;
import br.com.fiap.tech_challenge.adapters.driver.controller.model.enums.SituacaoPedidoDTO;
import br.com.fiap.tech_challenge.adapters.driver.controller.model.request.CadastrarPedidoDTO;
import br.com.fiap.tech_challenge.adapters.driver.controller.model.response.CadastrarPedidoResponseDTO;
import br.com.fiap.tech_challenge.adapters.driver.controller.model.response.PedidoResponseDTO;
import br.com.fiap.tech_challenge.adapters.driver.controller.model.response.StatusPedidoReponseDTO;
import br.com.fiap.tech_challenge.core.application.exception.cliente.ClienteNaoEncontradoException;
import br.com.fiap.tech_challenge.core.application.exception.pedido.ErroAoAtualizarPedidoException;
import br.com.fiap.tech_challenge.core.application.exception.pedido.ErroAoCadastrarPedidoException;
import br.com.fiap.tech_challenge.core.application.exception.pedido.NenhumPedidoEncontradoException;
import br.com.fiap.tech_challenge.core.application.exception.produto.NenhumProdutoEncontradoException;
import br.com.fiap.tech_challenge.core.application.ports.gateway.ClienteGatewayPort;
import br.com.fiap.tech_challenge.core.application.ports.gateway.PedidoGatewayPort;
import br.com.fiap.tech_challenge.core.application.ports.gateway.ProdutoGatewayPort;
import br.com.fiap.tech_challenge.core.application.ports.gateway.WebhookGatewayPort;
import br.com.fiap.tech_challenge.core.application.usecase.PedidoUseCase;
import br.com.fiap.tech_challenge.core.domain.model.Cliente;
import br.com.fiap.tech_challenge.core.domain.model.ItemPedido;
import br.com.fiap.tech_challenge.core.domain.model.Pedido;
import br.com.fiap.tech_challenge.core.domain.model.Produto;
import br.com.fiap.tech_challenge.core.domain.model.enums.SituacaoPedido;
import com.google.gson.Gson;
import com.mercadopago.resources.payment.Payment;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static br.com.fiap.tech_challenge.core.application.constant.ClienteExceptionConstante.CLIENTE_NAO_ENCONTRADO_EXCEPTION;
import static br.com.fiap.tech_challenge.core.application.constant.PedidoExceptionConstante.*;
import static br.com.fiap.tech_challenge.core.application.constant.ProdutoExceptionConstante.ERRO_AO_CONSULTAR_PRODUTO_POR_ID_EXCEPTION;

@Service
public class PedidoUseCaseImpl implements PedidoUseCase {

  private static final Logger LOGGER = LoggerFactory.getLogger(PedidoUseCaseImpl.class);
  private final ClienteGatewayPort clienteGatewayPort;
  private final PedidoGatewayPort pedidoGatewayPort;
  private final ProdutoGatewayPort produtoGatewayPort;
  private final WebhookGatewayPort webhookGatewayPort;

  public PedidoUseCaseImpl(ClienteGatewayPort clienteGatewayPort,
                           PedidoGatewayPort pedidoGatewayPort,
                           ProdutoGatewayPort produtoGatewayPort,
                           WebhookGatewayPort webhookGatewayPort) {
    this.clienteGatewayPort = clienteGatewayPort;
    this.pedidoGatewayPort = pedidoGatewayPort;
    this.produtoGatewayPort = produtoGatewayPort;
    this.webhookGatewayPort = webhookGatewayPort;
  }

  @Override
  public CadastrarPedidoResponseDTO cadastrarPedido(CadastrarPedidoDTO cadastrar) {
    try {
      Pedido pedido = new PedidoDTOMapperImpl().cadastrarToPedido(cadastrar);
      Cliente cliente = clienteGatewayPort.findById(pedido.getClientId());
      if(cliente == null) {
        throw new EntityNotFoundException();
      }

      List<ItemPedido> itemPedidos = new ArrayList<>();
      for(ItemPedido item: pedido.getItens()) {
        Produto produto = produtoGatewayPort.findById(item.getProduto().getProdutoId());
        if (produto == null) {
          throw new NenhumProdutoEncontradoException(
              ERRO_AO_CONSULTAR_PRODUTO_POR_ID_EXCEPTION +
                  item.getProduto().getProdutoId().toString());
        }

        item.setProduto(produto);
        item.setValorUnitario(produto.getPreco());
        item.setValorTotalPedido(getValorTotalItem(item));
        itemPedidos.add(item);
      }

      pedido.setItens(itemPedidos);
      pedido.setSituacaoPedido(SituacaoPedido.AGUARDANDO_PAGAMENTO);
      pedido.setDataPedido(new Date());
      pedido.setValorTotalPedido(getValorTotalPedido(pedido.getItens()));

      Payment pagamento = webhookGatewayPort.processarPagamentoWebhookMP(pedido, cliente);
      pedido.setMercadoPagoId(pagamento.getId());

      Long idPedido = pedidoGatewayPort.cadastrarPedidos(pedido, cliente);

      return CadastrarPedidoResponseDTO.builder()
          .idPedido(idPedido)
          .qrCode(pagamento.getPointOfInteraction().getTransactionData().getQrCode())
          .qrCodeBase64(pagamento.getPointOfInteraction().getTransactionData().getQrCodeBase64())
          .qrCodeUrl(pagamento.getPointOfInteraction().getTransactionData().getTicketUrl())
          .build();

    } catch (EntityNotFoundException e) {
      LOGGER.error(e.getMessage(), e);
      throw new ClienteNaoEncontradoException(CLIENTE_NAO_ENCONTRADO_EXCEPTION, e);
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      throw new ErroAoCadastrarPedidoException(ERRO_AO_CADASTRAR_PEDIDO_EXCEPTION, e);
    }

  }

  @Override
  public List<PedidoResponseDTO> listarPedidos() {
    List<Pedido> pedidos = pedidoGatewayPort.listaPedidos();
    if (pedidos.isEmpty()) {
      throw new NenhumPedidoEncontradoException(NENHUM_PEDIDO_FOI_ENCONTRADO_EXCEPTION);
    }
    pedidos.sort(
        Comparator.comparingInt((Pedido p) -> p.getSituacaoPedido().getOrdem()).reversed()
            .thenComparing(Pedido::getDataPedido));
    LOGGER.info(new Gson().toJson(pedidos));

    return new PedidoDTOMapperImpl().pedidosToPedidosResponseDTO(pedidos);
  }

  @Override
  public StatusPedidoReponseDTO consultaStatusPedido(Long id) {
    Pedido pedido = pedidoGatewayPort.consultaStatusPedidoPorId(id);
    if(pedido != null && !pedido.getSituacaoPedido().equals(SituacaoPedido.FINALIZADO)) {
        SituacaoPedido situacaoPedido = pedido.getSituacaoPedido();
        SituacaoPedidoDTO situacaoPedidoDTO = SituacaoPedidoDTO.valueOf(situacaoPedido.name());
        StatusPedidoReponseDTO responseDTO = new StatusPedidoReponseDTO();
        responseDTO.setIdPedido(id);
        responseDTO.setSituacaoPedidoDTO(situacaoPedidoDTO);
        return responseDTO;
    }
    throw new NenhumPedidoEncontradoException(NENHUM_PEDIDO_FOI_ENCONTRADO_EXCEPTION);

  }

  @Override
  public StatusPedidoReponseDTO atualizarStatusPedido(Long id, SituacaoPedidoDTO situacaoPedido) {
    try {
      return pedidoGatewayPort.atualizaStatusPedido(id, situacaoPedido);
    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      throw new ErroAoAtualizarPedidoException(ERRO_AO_ATUALIZAR_PEDIDO_EXCEPTION, e);
    }
  }

  private BigDecimal getValorTotalItem(ItemPedido item) {
    return item.getValorUnitario().multiply(BigDecimal.valueOf(item.getQuantidade()));
  }

  private BigDecimal getValorTotalPedido(List<ItemPedido> itensPedido) {
    return itensPedido.stream()
        .map(item -> item.getValorUnitario().multiply(BigDecimal.valueOf(item.getQuantidade())))
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

}
