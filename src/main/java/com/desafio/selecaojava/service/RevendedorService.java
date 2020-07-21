package com.desafio.selecaojava.service;

import com.desafio.selecaojava.domain.EstadoEnum;
import com.desafio.selecaojava.domain.Municipio;
import com.desafio.selecaojava.domain.Revendedor;
import com.desafio.selecaojava.domain.repository.RevendedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RevendedorService extends GenericServiceImpl<Revendedor> {


    private final RevendedorRepository revendedorRepository = (RevendedorRepository) this.genericRepository;
    private final MunicipioService municipioService;

    public RevendedorService(RevendedorRepository revendedorRepository,
                             MunicipioService municipioService) {
        super(revendedorRepository);
        this.municipioService = municipioService;
    }

    public List<Revendedor> pesquisarPorNomeMunicipio(String nome) {
        Municipio municipio = municipioService.pesquisarPorNome(nome);
        return revendedorRepository.findAllByMunicipio(municipio);
    }

    public List<Revendedor> pesquisarPorNomeEEstadoMunicipio(String nome, EstadoEnum estado) {
        Municipio municipio = municipioService.pesquisarPorNomeEEstado(nome, estado);
        return revendedorRepository.findAllByMunicipio(municipio);
    }

    public List<Revendedor> pesquisarPorRegiaoMunicipio(String regiao) {
        Municipio municipio = municipioService.pesquisarPorRegiao(regiao);
        return revendedorRepository.findAllByMunicipio(municipio);
    }

    public List<Revendedor> pesquisarPorBandeira(String bandeira) {
        return revendedorRepository.findAllByBandeira(bandeira);
    }

    public List<Revendedor> pesquisarPorNome(String nome) {
        return revendedorRepository.findAllByNome(nome);
    }

    public List<Revendedor> pesquisarPorCnpj(String cnpj) {
        return revendedorRepository.findAllByCnpj(cnpj);
    }
}
