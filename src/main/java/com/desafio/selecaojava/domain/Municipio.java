package com.desafio.selecaojava.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "municipios")
public class Municipio {

    @Id
    @GeneratedValue(generator = "s_municipios")
    @SequenceGenerator(name = "s_municipios", sequenceName = "s_municipios", allocationSize = 1)
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private EstadoEnum estado;

    @Enumerated(EnumType.STRING)
    private RegiaoEnum regiao;


    public Municipio (String nome,
                      EstadoEnum estado,
                      RegiaoEnum regiao) {
        this.nome = nome;
        this.estado = estado;
        this.regiao = regiao;
    }

}
