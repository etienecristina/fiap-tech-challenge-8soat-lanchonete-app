package br.com.fiap.tech_challenge.core.application.usecase;

import br.com.fiap.tech_challenge.adapters.driver.controller.mapper.PedidoDTOMapperImpl;
import br.com.fiap.tech_challenge.core.application.ports.gateway.ClienteGatewayPort;
import br.com.fiap.tech_challenge.core.application.ports.gateway.PedidoGatewayPort;
import br.com.fiap.tech_challenge.core.application.ports.gateway.ProdutoGatewayPort;
import br.com.fiap.tech_challenge.core.application.ports.gateway.WebhookGatewayPort;
import br.com.fiap.tech_challenge.core.application.usecase.impl.PedidoUseCaseImpl;
import br.com.fiap.tech_challenge.core.domain.model.Pedido;
import br.com.fiap.tech_challenge.core.domain.model.enums.SituacaoPedido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PedidoUseCaseImplTest {

  @InjectMocks private PedidoUseCaseImpl pedidoUseCase;
  @Mock private ClienteGatewayPort clienteGatewayPort;
  @Mock private PedidoGatewayPort pedidoGatewayPort;
  @Mock private ProdutoGatewayPort produtoGatewayPort;
  @Mock private WebhookGatewayPort webhookGatewayPort;

  @BeforeEach
  public void setUp() {
    pedidoUseCase = new PedidoUseCaseImpl(
        clienteGatewayPort,
        pedidoGatewayPort,
        produtoGatewayPort,
        webhookGatewayPort);
  }

  @Test
  public void testListarPedidos() throws ParseException {
    List<Pedido> pedidos = new ArrayList<>();
    pedidos.add(new Pedido("1", 100L, getDate("25/09/2024 14:32:41"), new BigDecimal(100), SituacaoPedido.PAGAMENTO_RECEBIDO, 1L, new ArrayList<>()));
    pedidos.add(new Pedido("2", 102L, getDate("25/09/2024 14:15:30"), new BigDecimal(100), SituacaoPedido.PRONTO_PARA_RETIRADA, 1L, new ArrayList<>()));
    pedidos.add(new Pedido("3", 200L, getDate("25/09/2024 14:15:34"), new BigDecimal(100), SituacaoPedido.PAGAMENTO_RECEBIDO, 1L, new ArrayList<>()));
    pedidos.add(new Pedido("4", 200L, getDate("25/09/2024 13:15:30"), new BigDecimal(100), SituacaoPedido.EM_PREPARACAO, 1L, new ArrayList<>()));
    pedidos.add(new Pedido("5", 200L, getDate("25/09/2024 13:00:08"), new BigDecimal(100), SituacaoPedido.EM_PREPARACAO, 1L, new ArrayList<>()));

    List<Pedido> resultadoEsperado = new ArrayList<>();
    resultadoEsperado.add(new Pedido("2", 102L, getDate("25/09/2024 14:15:30"), new BigDecimal(100), SituacaoPedido.PRONTO_PARA_RETIRADA, 1L, new ArrayList<>()));
    resultadoEsperado.add(new Pedido("5", 200L, getDate("25/09/2024 13:00:08"), new BigDecimal(100), SituacaoPedido.EM_PREPARACAO, 1L, new ArrayList<>()));
    resultadoEsperado.add(new Pedido("4", 200L, getDate("25/09/2024 13:15:30"), new BigDecimal(100), SituacaoPedido.EM_PREPARACAO, 1L, new ArrayList<>()));
    resultadoEsperado.add(new Pedido("3", 200L, getDate("25/09/2024 14:15:34"), new BigDecimal(100), SituacaoPedido.PAGAMENTO_RECEBIDO, 1L, new ArrayList<>()));
    resultadoEsperado.add(new Pedido("1", 100L, getDate("25/09/2024 14:32:41"), new BigDecimal(100), SituacaoPedido.PAGAMENTO_RECEBIDO, 1L, new ArrayList<>()));

    when(pedidoGatewayPort.listaPedidos()).thenReturn(pedidos);
    var retorno = pedidoUseCase.listarPedidos();
    assertEquals(new PedidoDTOMapperImpl().pedidosToPedidosResponseDTO(resultadoEsperado), retorno);

  }

  private Date getDate(String date) throws ParseException {
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.US);
    return formatter.parse(date);
  }

}
