package com.desafio.selecaojava.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "revendedores")
public class Revendedor {

    @Id
    @GeneratedValue(generator = "s_revendedores")
    @SequenceGenerator(name = "s_revendedores", sequenceName = "s_revendedores", allocationSize = 1)
    private Long id;

    private String nome;

    private String cnpj;

    private String bandeira;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Municipio municipio;

    public Revendedor (String nome,
                       String cnpj,
                       String bandeira,
                       Municipio municipio) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.bandeira = bandeira;
        this.municipio = municipio;
    }

}
