package br.com.fiap.tech_challenge.core.application.mapper;

import br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.entity.ClienteEntity;
import br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.entity.EnderecoEntity;
import br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.entity.TelefoneEntity;
import br.com.fiap.tech_challenge.core.domain.model.Cliente;
import br.com.fiap.tech_challenge.core.domain.model.Endereco;
import br.com.fiap.tech_challenge.core.domain.model.Telefone;
import org.mapstruct.*;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ClienteMapper {

    @Mapping(source = "entity.telefones", target = "telefones", qualifiedByName = "telefoneEntityToTelefone")
    @Mapping(source = "entity.enderecos", target = "enderecos", qualifiedByName = "enderecoEntityToEndereco")
    Cliente toCliente(ClienteEntity entity);

    @Mapping(source = "cliente.telefones", target = "telefones", qualifiedByName = "telefoneToTelefoneEntity")
    @Mapping(source = "cliente.enderecos", target = "enderecos", qualifiedByName = "enderecoToEnderecoEntity")
    ClienteEntity toClienteEntity(Cliente cliente);

    @Named("telefoneEntityToTelefone")
    default List<Telefone> telefoneEntityToTelefoneDto(List<TelefoneEntity> telefoneEntities) {
        List<Telefone> telefoneList = new ArrayList<>();
        telefoneEntities.forEach(telefone -> {
            Telefone telefoneDTO = new Telefone();
            BeanUtils.copyProperties(telefone, telefoneDTO);
            telefoneList.add(telefoneDTO);
        });

        return telefoneList;
    }

    @Named("enderecoEntityToEndereco")
    default List<Endereco> enderecoEntityToEnderecoDto(List<EnderecoEntity> enderecoEntities) {
        List<Endereco> enderecoList = new ArrayList<>();
        enderecoEntities.forEach(endereco -> {
            Endereco enderecoDTO = new Endereco();
            BeanUtils.copyProperties(endereco, enderecoDTO);
            enderecoList.add(enderecoDTO);
        });

        return enderecoList;
    }

    @Named("telefoneToTelefoneEntity")
    default List<TelefoneEntity> telefoneToTelefoneEntity(List<Telefone> telefoneList) {
        List<TelefoneEntity> telefoneEntities = new ArrayList<>();
        telefoneList.forEach(telefone -> {
            TelefoneEntity entity = new TelefoneEntity();
            BeanUtils.copyProperties(telefone, entity);
            telefoneEntities.add(entity);
        });

        return telefoneEntities;
    }

    @Named("enderecoToEnderecoEntity")
    default List<EnderecoEntity> enderecoToEnderecoEntity(List<Endereco> enderecos) {
        List<EnderecoEntity> enderecoEntities = new ArrayList<>();
        enderecos.forEach(endereco -> {
            EnderecoEntity entity = new EnderecoEntity();
            BeanUtils.copyProperties(endereco, entity);
            enderecoEntities.add(entity);
        });

        return enderecoEntities;
    }

}
