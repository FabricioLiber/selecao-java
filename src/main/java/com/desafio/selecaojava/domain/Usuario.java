package com.desafio.selecaojava.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(generator = "s_usuarios")
    @SequenceGenerator(name = "s_usuarios", sequenceName = "s_usuarios", allocationSize = 1)
    private Long id;

    private String nome;

    private String cpf;

    private String email;

    private String password;

}
