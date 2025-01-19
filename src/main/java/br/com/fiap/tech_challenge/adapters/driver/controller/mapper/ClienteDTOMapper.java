package br.com.fiap.tech_challenge.adapters.driver.controller.mapper;

import br.com.fiap.tech_challenge.adapters.driver.controller.model.request.AtualizarClienteDTO;
import br.com.fiap.tech_challenge.adapters.driver.controller.model.request.CadastrarClienteDTO;
import br.com.fiap.tech_challenge.adapters.driver.controller.model.request.EnderecoDTO;
import br.com.fiap.tech_challenge.adapters.driver.controller.model.request.TelefoneDTO;
import br.com.fiap.tech_challenge.core.domain.model.Cliente;
import br.com.fiap.tech_challenge.core.domain.model.Endereco;
import br.com.fiap.tech_challenge.core.domain.model.Telefone;
import br.com.fiap.tech_challenge.core.domain.model.enums.TipoTelefone;
import org.mapstruct.*;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring",
    unmappedSourcePolicy = ReportingPolicy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ClienteDTOMapper {
  @Mapping(source = "cadastrar.telefones", target = "telefones", qualifiedByName = "cadastrarToTelefones")
  @Mapping(source = "cadastrar.enderecos", target = "enderecos", qualifiedByName = "cadastrarToEnderecos")
  Cliente cadastrarToCliente(CadastrarClienteDTO cadastrar);

  @Mapping(source = "atualizar.telefones", target = "telefones", qualifiedByName = "atualizarToTelefones")
  @Mapping(source = "atualizar.enderecos", target = "enderecos", qualifiedByName = "atualizarToEnderecos")
  Cliente atualizarToCliente(AtualizarClienteDTO atualizar);

  @Named("cadastrarToTelefones")
  default List<Telefone> cadastrarToTelefones(List<TelefoneDTO> telefoneDTOS) {
    List<Telefone> telefoneList = new ArrayList<>();
    telefoneDTOS.forEach(telefoneDTO -> {
      Telefone telefone = new Telefone();
      BeanUtils.copyProperties(telefoneDTO, telefone);
      telefone.setTipoTelefone(
          TipoTelefone.valueOf(
              telefoneDTO.getTipoTelefone().name()));
      telefoneList.add(telefone);
    });

    return telefoneList;
  }

  @Named("cadastrarToEnderecos")
  default List<Endereco> cadastrarToEnderecos(List<EnderecoDTO> enderecoDTOS) {
    List<Endereco> enderecoList = new ArrayList<>();
    enderecoDTOS.forEach(enderecoDTO -> {
      Endereco endereco = new Endereco();
      BeanUtils.copyProperties(enderecoDTO, endereco);
      enderecoList.add(endereco);
    });

    return enderecoList;
  }

  @Named("atualizarToTelefones")
  default List<Telefone> atualizarToTelefones(List<TelefoneDTO> telefoneDTOS) {
    List<Telefone> telefoneList = new ArrayList<>();
    telefoneDTOS.forEach(telefoneDTO -> {
      Telefone telefone = new Telefone();
      BeanUtils.copyProperties(telefoneDTO, telefone);
      telefone.setTipoTelefone(
          TipoTelefone.valueOf(
              telefoneDTO.getTipoTelefone().name()));
      telefoneList.add(telefone);
    });

    return telefoneList;
  }

  @Named("atualizarToEnderecos")
  default List<Endereco> atualizarToEnderecos(List<EnderecoDTO> enderecoDTOS) {
    List<Endereco> enderecoList = new ArrayList<>();
    enderecoDTOS.forEach(enderecoDTO -> {
      Endereco endereco = new Endereco();
      BeanUtils.copyProperties(enderecoDTO, endereco);
      enderecoList.add(endereco);
    });

    return enderecoList;
  }

}
