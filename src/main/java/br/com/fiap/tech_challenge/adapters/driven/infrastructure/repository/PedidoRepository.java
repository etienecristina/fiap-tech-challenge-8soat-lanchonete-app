package br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository;

import br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Integer> {
  @Query("SELECT p " +
      "FROM PedidoEntity p " + "WHERE " +
      "p.situacao <> br.com.fiap.tech_challenge.core.domain.model.enums.SituacaoPedido.FINALIZADO")
  List<PedidoEntity> findAllWithActiveStatus();
  Optional<PedidoEntity> findByMercadoPagoId(Long idMercadoPago);

}
