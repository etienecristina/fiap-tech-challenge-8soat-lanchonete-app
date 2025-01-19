package br.com.fiap.tech_challenge.adapters.driver.controller.model.response;

import lombok.Data;

import java.util.List;

@Data
public class PedidosResponseDTO {

  private List<PedidoResponseDTO> pedidos;

}
