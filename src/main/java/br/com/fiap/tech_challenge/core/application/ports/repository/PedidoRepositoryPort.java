package br.com.fiap.tech_challenge.core.application.ports.repository;

import br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.entity.PedidoEntity;

import java.util.List;
import java.util.Optional;

public interface PedidoRepositoryPort {
  void cadastrarPedidos(PedidoEntity pedido);
  List<PedidoEntity> listaPedidos();
  Optional<PedidoEntity> consultaStatusPedidoPorId(Long id);

}
