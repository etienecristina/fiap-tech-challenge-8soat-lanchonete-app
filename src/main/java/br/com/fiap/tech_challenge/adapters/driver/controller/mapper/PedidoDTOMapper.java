package br.com.fiap.tech_challenge.adapters.driver.controller.mapper;

import br.com.fiap.tech_challenge.adapters.driver.controller.model.request.CadastrarPedidoDTO;
import br.com.fiap.tech_challenge.adapters.driver.controller.model.response.ItemPedidoResponseDTO;
import br.com.fiap.tech_challenge.adapters.driver.controller.model.response.ItemProdutoResponseDTO;
import br.com.fiap.tech_challenge.adapters.driver.controller.model.response.PedidoResponseDTO;
import br.com.fiap.tech_challenge.core.domain.model.ItemPedido;
import br.com.fiap.tech_challenge.core.domain.model.Pedido;
import org.mapstruct.*;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring",
    unmappedSourcePolicy = ReportingPolicy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface PedidoDTOMapper {
  Pedido cadastrarToPedido(CadastrarPedidoDTO cadastrar);

  @Mapping(source = "pedidos.itens", target = "itens", qualifiedByName = "itensDomainToItensResponse")
  List<PedidoResponseDTO> pedidosToPedidosResponseDTO(List<Pedido> pedidos);

  @Named("itensDomainToItensResponse")
  default List<ItemPedidoResponseDTO> itensDomainToItensResponse(List<ItemPedido> itens) {
    List<ItemPedidoResponseDTO> itensResponseDto = new ArrayList<>();
    itens.forEach(item -> {
      ItemPedidoResponseDTO itemPedidoResponseDTO = new ItemPedidoResponseDTO();
      BeanUtils.copyProperties(item, itemPedidoResponseDTO);

      ItemProdutoResponseDTO produtoResponseDTO = new ItemProdutoResponseDTO();
      BeanUtils.copyProperties(item.getProduto(), produtoResponseDTO);
      itemPedidoResponseDTO.setProduto(produtoResponseDTO);

      itensResponseDto.add(itemPedidoResponseDTO);
    });

    return itensResponseDto;
  }

}
