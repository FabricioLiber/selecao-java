package com.desafio.selecaojava.service;

import com.desafio.selecaojava.domain.Municipio;
import com.desafio.selecaojava.domain.repository.MunicipioRepository;
import org.springframework.stereotype.Service;

@Service
public class MunicipioService extends GenericServiceImpl<Municipio> {

    private final MunicipioRepository municipioRepository = (MunicipioRepository) this.genericRepository;

    public MunicipioService (MunicipioRepository municipioRepository) {
        super(municipioRepository);
    }

    public Municipio pesquisarPorNome(String nome) {
        return ((MunicipioRepository) this.genericRepository).findByNome(nome);
    }

    public Municipio pesquisarPorRegiao(String regiao) {
        return ((MunicipioRepository) this.genericRepository).findByRegiao(regiao);
    }


}
