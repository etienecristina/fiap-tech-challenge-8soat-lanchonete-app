package br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.entity;

import br.com.fiap.tech_challenge.core.domain.model.enums.TipoTelefone;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "TELEFONE")
public class TelefoneEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ClienteEntity cliente;

    @Column(nullable = false)
    private TipoTelefone tipoTelefone;

    @Column(nullable = false, length = 2)
    @Pattern(regexp = "^[1-9][0-9]$", message = "O DDD deve ter dois dígitos e não pode começar com zero")
    private String ddd;

    @Column(nullable = false, length = 9)
    @Pattern(regexp = "^[1-9][0-9]{7,8}$", message = "O número do telefone deve ter 8 ou 9 dígitos e não pode começar com zero")
    private String numero;

}
