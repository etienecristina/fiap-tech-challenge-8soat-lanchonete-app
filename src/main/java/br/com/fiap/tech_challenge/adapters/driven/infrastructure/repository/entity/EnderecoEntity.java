package br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "ENDERECO")
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ClienteEntity cliente;

    @Column(nullable = false, length = 8)
    @Pattern(regexp = "\\d{7}", message = "O cep deve ter 7 dígitos numéricos")
    private String cep;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "Informe o logradouro")
    private String logradouro;

    // @Column(nullable = true, length = 4)
    // Tem endereço que não tem número
    // private int numero;

    @Column(length = 40)
    private String complemento;

    @Column(nullable = false, length = 30)
    @NotBlank(message = "Informe o bairro")
    private String bairro;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "Informe a cidade")
    private String cidade;

    @Column(nullable = false, length = 30)
    @NotBlank(message = "Informe o estado")
    private String estado;

}
