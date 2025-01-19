package br.com.fiap.tech_challenge.core.application.ports.gateway;

import br.com.fiap.tech_challenge.core.domain.model.Cliente;

public interface ClienteGatewayPort {
  Cliente findById(Long id);
  Cliente buscarPorCpf(String cpf);
  void save(Cliente cliente) throws Exception;
  boolean existsById(Long id);
  void excluirPorId(Long id);

}
