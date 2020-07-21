package com.desafio.selecaojava.domain.repository;

import com.desafio.selecaojava.domain.EstadoEnum;
import com.desafio.selecaojava.domain.Municipio;

public interface MunicipioRepository extends GenericRepository<Municipio> {

    Municipio findByNome(String nome);

    Municipio findByRegiao(String regiao);

    Municipio findByNomeAndEstado(String nome, EstadoEnum estado);

}
