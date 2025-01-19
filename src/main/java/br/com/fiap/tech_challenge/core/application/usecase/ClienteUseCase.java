package br.com.fiap.tech_challenge.core.application.usecase;

import br.com.fiap.tech_challenge.adapters.driver.controller.model.request.AtualizarClienteDTO;
import br.com.fiap.tech_challenge.adapters.driver.controller.model.request.CadastrarClienteDTO;
import br.com.fiap.tech_challenge.core.domain.model.Cliente;

public interface ClienteUseCase {
    void cadastrarCliente(CadastrarClienteDTO cadastrar);
    Cliente buscarClientePorCPF(String cpf);
    void atualizarCliente(AtualizarClienteDTO atualizar);
    String excluirCliente(Long id);
}
