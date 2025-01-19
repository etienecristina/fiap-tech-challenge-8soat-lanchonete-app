package br.com.fiap.tech_challenge.core.application.usecase.impl;

import br.com.fiap.tech_challenge.adapters.driver.controller.mapper.ClienteDTOMapper;
import br.com.fiap.tech_challenge.adapters.driver.controller.mapper.ClienteDTOMapperImpl;
import br.com.fiap.tech_challenge.adapters.driver.controller.model.request.AtualizarClienteDTO;
import br.com.fiap.tech_challenge.adapters.driver.controller.model.request.CadastrarClienteDTO;
import br.com.fiap.tech_challenge.core.application.exception.cliente.*;
import br.com.fiap.tech_challenge.core.application.ports.gateway.ClienteGatewayPort;
import br.com.fiap.tech_challenge.core.application.usecase.ClienteUseCase;
import br.com.fiap.tech_challenge.core.domain.exception.DomainException;
import br.com.fiap.tech_challenge.core.domain.model.Cliente;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static br.com.fiap.tech_challenge.core.application.constant.ClienteExceptionConstante.*;

@Service
public class ClienteUseCaseImpl implements ClienteUseCase {

    private final ClienteGatewayPort gatewayPort;

    public ClienteUseCaseImpl(ClienteGatewayPort clienteGatewayPort) {
        this.gatewayPort = clienteGatewayPort;
    }

    @Override
    public void cadastrarCliente(CadastrarClienteDTO cadastrar) {
        if(gatewayPort.buscarPorCpf(cadastrar.getCpf()) != null) {
            throw new ClienteJaCadastradoException(CLIENTE_JA_CADASTRADO_EXCEPTION);
        }

        try {
            ClienteDTOMapper clienteDTOMapper = new ClienteDTOMapperImpl();
            var cliente = clienteDTOMapper.cadastrarToCliente(cadastrar);
            gatewayPort.save(cliente);
        } catch (Exception e) {
            throw new ErroAoCadastrarClienteException(ERRO_AO_CADASTRAR_CLIENTE_EXCEPTION);
        }
    }

    @Override
    public Cliente buscarClientePorCPF(String cpf) {
        Cliente cliente = gatewayPort.buscarPorCpf(cpf);
        if(cliente != null) {
            return cliente;
        }
        else {
            throw new ClienteNaoEncontradoException(CLIENTE_NAO_ENCONTRADO_EXCEPTION);
        }
    }

    @Override
    public void atualizarCliente(AtualizarClienteDTO atualizar) {
        Long clienteId = atualizar.getId();
        if(gatewayPort.findById(clienteId) == null) {
            throw new ClienteNaoEncontradoException(CLIENTE_NAO_ENCONTRADO_EXCEPTION);
        }

        try {
            Cliente cliente = gatewayPort.findById(clienteId);
            cliente.validarDadosAtualizacao(atualizar);

            ClienteDTOMapper clienteDTOMapper = new ClienteDTOMapperImpl();
            cliente = clienteDTOMapper.atualizarToCliente(atualizar);
            gatewayPort.save(cliente);
        }
        catch (DomainException e) {
            throw new ErroAoAtualizarAsInformacoesDoClienteException(
                e.getMessage());
        }
        catch (Exception e) {
            throw new ErroAoAtualizarAsInformacoesDoClienteException(
                ERRO_AO_ATUALIZAR_AS_INFORMACOES_DO_CLIENTE_EXCEPTION);
        }
    }

    @Override
    public String excluirCliente(Long id) {
        if(!gatewayPort.existsById(id)) {
            throw new ClienteNaoEncontradoException(CLIENTE_NAO_ENCONTRADO_EXCEPTION);
        }

        try {
            gatewayPort.excluirPorId(id);
            return "Cliente excluído com sucesso!";
        }
        catch (Exception e) {
            throw new ErroAoExcluirClienteException(ERRO_AO_EXCLUIR_CLIENTE_EXCEPTION);
        }
    }


}
