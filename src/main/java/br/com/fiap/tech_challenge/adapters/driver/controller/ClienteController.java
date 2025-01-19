package br.com.fiap.tech_challenge.adapters.driver.controller;

import br.com.fiap.tech_challenge.adapters.driven.infrastructure.gateway.ClienteGateway;
import br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.ClienteRepository;
import br.com.fiap.tech_challenge.adapters.driver.controller.model.request.AtualizarClienteDTO;
import br.com.fiap.tech_challenge.adapters.driver.controller.model.request.CadastrarClienteDTO;
import br.com.fiap.tech_challenge.adapters.driver.controller.swagger.ClienteSwaggerInterface;
import br.com.fiap.tech_challenge.core.application.usecase.impl.ClienteUseCaseImpl;
import br.com.fiap.tech_challenge.core.domain.model.Cliente;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/cliente")
public class ClienteController implements ClienteSwaggerInterface {

    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public ResponseEntity<String> cadastrarCliente(@RequestBody CadastrarClienteDTO cadastrar) {
        var clienteGateway = new ClienteGateway(this.clienteRepository);
        var clienteUseCase = new ClienteUseCaseImpl(clienteGateway);

        clienteUseCase.cadastrarCliente(cadastrar);
        return new ResponseEntity<>("Cliente cadastrado com sucesso!", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Cliente> buscarClientePorCPF(@PathVariable String cpf) {
        var clienteGateway = new ClienteGateway(this.clienteRepository);
        var clienteUseCase = new ClienteUseCaseImpl(clienteGateway);

        Cliente cliente = clienteUseCase.buscarClientePorCPF(cpf);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> atualizarCliente(@RequestBody AtualizarClienteDTO atualizar) {
        var clienteGateway = new ClienteGateway(this.clienteRepository);
        var clienteUseCase = new ClienteUseCaseImpl(clienteGateway);

        clienteUseCase.atualizarCliente(atualizar);
        return new ResponseEntity<>("Cliente atualizado com sucesso!", HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<String> excluirCliente (@PathVariable Long id) {
        var clienteGateway = new ClienteGateway(this.clienteRepository);
        var clienteUseCase = new ClienteUseCaseImpl(clienteGateway);

        return new ResponseEntity<>(clienteUseCase.excluirCliente(id), HttpStatus.OK);
    }

}
