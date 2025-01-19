package br.com.fiap.tech_challenge.core.domain.model;

import br.com.fiap.tech_challenge.adapters.driver.controller.model.request.AtualizarClienteDTO;
import br.com.fiap.tech_challenge.core.domain.exception.DomainException;
import lombok.Data;

import java.util.List;

@Data
public class Cliente {

    private Long id;
    private String cpf;
    private String primeiroNome;
    private String sobrenome;
    private String email;
    private List<Telefone> telefones;
    private List<Endereco> enderecos;

    public String getCpf() {
        return cpf.replaceAll("[./-]", "");
    }

    public void validarDadosAtualizacao(AtualizarClienteDTO atualizar) throws DomainException {
        if(!this.id.equals(atualizar.getId())) {
            throw new DomainException("Não é possível alterar id de cliente já existente");
        }
        if(!this.cpf.equals(atualizar.getCpf())) {
            throw new DomainException("Não é possível alterar cpf de cliente já existente");
        }
        if (atualizar.getEmail().matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}")) {
            throw new DomainException("E-mail inválido!");
        }
    }
}
