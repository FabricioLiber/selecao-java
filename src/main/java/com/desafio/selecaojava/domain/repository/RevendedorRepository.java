package com.desafio.selecaojava.domain.repository;

import com.desafio.selecaojava.domain.Municipio;
import com.desafio.selecaojava.domain.Revendedor;

import java.util.List;

public interface RevendedorRepository extends GenericRepository<Revendedor> {

    List<Revendedor> findAllByMunicipio(Municipio municipio);

    List<Revendedor> findAllByBandeira(String bandeira);

    List<Revendedor> findAllByNome(String nome);

    List<Revendedor> findAllByCnpj(String cnpj);
}
