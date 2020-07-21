package com.desafio.selecaojava.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(generator = "s_produtos")
    @SequenceGenerator(name = "s_produtos", sequenceName = "s_produtos", allocationSize = 1)
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private UnidadeMedidaEnum unidadeMedida;

    public Produto (String nome, UnidadeMedidaEnum unidadeMedida) {
        this.nome = nome;
        this.unidadeMedida = unidadeMedida;
    }

}
