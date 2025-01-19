package br.com.fiap.tech_challenge.adapters.driven.infrastructure.gateway;

import br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.PedidoRepository;
import br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.entity.ClienteEntity;
import br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.entity.PedidoEntity;
import br.com.fiap.tech_challenge.adapters.driver.controller.model.enums.SituacaoPedidoDTO;
import br.com.fiap.tech_challenge.adapters.driver.controller.model.response.StatusPedidoReponseDTO;
import br.com.fiap.tech_challenge.core.application.exception.pedido.ErroAoAtualizarPedidoException;
import br.com.fiap.tech_challenge.core.application.exception.pedido.NenhumPedidoEncontradoException;
import br.com.fiap.tech_challenge.core.application.mapper.ClienteMapperImpl;
import br.com.fiap.tech_challenge.core.application.mapper.PedidoMapperImpl;
import br.com.fiap.tech_challenge.core.application.ports.gateway.PedidoGatewayPort;
import br.com.fiap.tech_challenge.core.domain.model.Cliente;
import br.com.fiap.tech_challenge.core.domain.model.Pedido;
import br.com.fiap.tech_challenge.core.domain.model.enums.SituacaoPedido;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.com.fiap.tech_challenge.core.application.constant.PedidoExceptionConstante.ERRO_AO_ATUALIZAR_PEDIDO_EXCEPTION;
import static br.com.fiap.tech_challenge.core.application.constant.PedidoExceptionConstante.NENHUM_PEDIDO_FOI_ENCONTRADO_EXCEPTION;

@Component
public class PedidoGateway implements PedidoGatewayPort {
  private final PedidoRepository pedidoRepository;

  public PedidoGateway(PedidoRepository pedidoRepository) {
    this.pedidoRepository = pedidoRepository;
  }

  @Override
  public Long cadastrarPedidos(Pedido pedido, Cliente cliente) {
    PedidoEntity entity = new PedidoMapperImpl().toEntity(pedido);
    ClienteEntity clienteEntity = new ClienteMapperImpl().toClienteEntity(cliente);
    entity.setCliente(clienteEntity);
    return pedidoRepository.save(entity).getId();
  }

  @Override
  public List<Pedido> listaPedidos() {
    List<PedidoEntity> pedidoEntityList = pedidoRepository.findAllWithActiveStatus();
    List<Pedido> pedidos = new ArrayList<>();
    pedidoEntityList.forEach(
        entity -> pedidos.add(new PedidoMapperImpl().toDTO(entity))
    );
    return pedidos;
  }

  @Override
  public Pedido consultaStatusPedidoPorId(Long id) {
    Optional<PedidoEntity> pedidoEntity = pedidoRepository.findById(id.intValue());
    return pedidoEntity.map(new PedidoMapperImpl()::toDTO).orElse(null);
  }

  @Override
  public StatusPedidoReponseDTO atualizaStatusPedido(Long id, SituacaoPedidoDTO situacaoPedido) {
    try {
      var obj = pedidoRepository.findById(id.intValue());
      PedidoEntity pedidoEntity;
      if (obj.isPresent()) {
        pedidoEntity = obj.get();
        pedidoEntity.setSituacao(SituacaoPedido.valueOf(situacaoPedido.name()));
        pedidoEntity = pedidoRepository.save(pedidoEntity);
      }
      else {
        throw new NenhumPedidoEncontradoException(NENHUM_PEDIDO_FOI_ENCONTRADO_EXCEPTION);
      }

      StatusPedidoReponseDTO response = new StatusPedidoReponseDTO();
      response.setIdPedido(pedidoEntity.getId());
      response.setSituacaoPedidoDTO(
          SituacaoPedidoDTO.valueOf(pedidoEntity.getSituacao().name()));
      return response;
    } catch (Exception e) {
      throw new ErroAoAtualizarPedidoException(ERRO_AO_ATUALIZAR_PEDIDO_EXCEPTION, e);
    }
  }

  @Override
  public Pedido consultaStatusPedidoPorMercadoPagoId(Long mercadoPagoId) {
    Optional<PedidoEntity> pedido = pedidoRepository.findByMercadoPagoId(mercadoPagoId);
    return pedido.map(new PedidoMapperImpl()::toDTO).orElse(null);
  }

}
