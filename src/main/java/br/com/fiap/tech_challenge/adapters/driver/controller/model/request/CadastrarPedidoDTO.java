package br.com.fiap.tech_challenge.adapters.driver.controller.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CadastrarPedidoDTO {

    @Schema(name = "clientId", description = "Identificação do cliente", example = "1")
    @NotNull
    private Long clientId;

    @Schema(name = "itens", description = "Lista de itens do pedido")
    private List<ItemPedidoDTO> itens = new ArrayList<>();

}
