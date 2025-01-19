package br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository;

import br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

    Optional<ClienteEntity> findByCpf(String cpf);

}
