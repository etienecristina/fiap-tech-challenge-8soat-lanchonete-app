package br.com.fiap.tech_challenge.adapters.driven.infrastructure.gateway;

import br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.ClienteRepository;
import br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.entity.ClienteEntity;
import br.com.fiap.tech_challenge.core.application.mapper.ClienteMapper;
import br.com.fiap.tech_challenge.core.application.mapper.ClienteMapperImpl;
import br.com.fiap.tech_challenge.core.application.ports.gateway.ClienteGatewayPort;
import br.com.fiap.tech_challenge.core.domain.model.Cliente;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClienteGateway implements ClienteGatewayPort {

  private final ClienteRepository repository;

  public ClienteGateway(ClienteRepository repository) {
    this.repository = repository;
  }

  @Override
  public Cliente findById(Long id) {
    Optional<ClienteEntity> clienteEntity = repository.findById(id);
    ClienteMapper clienteMapper = new ClienteMapperImpl();
    return clienteEntity.map(clienteMapper::toCliente).orElse(null);
  }

  @Override
  public Cliente buscarPorCpf(String cpf) {
    Optional<ClienteEntity> clienteEntity = repository.findByCpf(cpf);
    ClienteMapper clienteMapper = new ClienteMapperImpl();
    return clienteEntity.map(clienteMapper::toCliente).orElse(null);
  }

  @Override
  public void save(Cliente cliente) throws Exception {
    try {
      ClienteMapper clienteMapper = new ClienteMapperImpl();
      ClienteEntity clienteEntity = clienteMapper.toClienteEntity(cliente);
      repository.save(clienteEntity);
    } catch (Exception e) {
      throw new Exception(e);
    }
  }

  @Override
  public boolean existsById(Long id) {
    return repository.existsById(id);
  }

  @Override
  public void excluirPorId(Long id) {
    repository.deleteById(id);
  }
}
