package br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "CLIENTE")
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cpf", nullable = false, length = 11, unique = true)
    @Size(min = 11, max = 11, message = "O cpf deve ter 11 caracteres")
    private String cpf;

    @Column(name = "primeiro_nome", nullable = false, length = 120)
    @NotBlank(message = "Informe o primeiro nome")
    private String primeiroNome;

    @Column(name = "sobrenome", nullable = false, length = 120)
    @NotBlank(message = "Informe o sobrenome")
    private String sobrenome;

    @Column(name = "email", nullable = true, length = 50)
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id")
    private List<TelefoneEntity> telefones = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id")
    private List<EnderecoEntity> enderecos = new ArrayList<>();

}
