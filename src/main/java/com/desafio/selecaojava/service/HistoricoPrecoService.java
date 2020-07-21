package com.desafio.selecaojava.service;

import com.desafio.selecaojava.domain.EstadoEnum;
import com.desafio.selecaojava.domain.HistoricoPreco;
import com.desafio.selecaojava.domain.Municipio;
import com.desafio.selecaojava.domain.Revendedor;
import com.desafio.selecaojava.domain.repository.HistoricoPrecoRepository;
import com.desafio.selecaojava.resource.dto.ValorDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class HistoricoPrecoService extends GenericServiceImpl<HistoricoPreco> {

    private final RevendedorService revendedorService;

    private final HistoricoPrecoRepository historicoPrecoRepository = (HistoricoPrecoRepository) genericRepository;

    public HistoricoPrecoService(HistoricoPrecoRepository historicoPrecoRepository,
                                 MunicipioService municipioService,
                                 RevendedorService revendedorService) {
        super(historicoPrecoRepository);
        this.revendedorService = revendedorService;
    }

    public Double calcularMedioPrecoPorMunicipio(String nomeMunicipio) {
        List<Revendedor> revendedores = revendedorService.pesquisarPorNomeMunicipio(nomeMunicipio);
        return historicoPrecoRepository.calcularPrecoMedioVendaPorRevendedores(revendedores);
    }

    public Page<HistoricoPreco> pesquisarPorRegiao(String regiao,
                                                   Integer page,
                                                   Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<Revendedor> revendedores = revendedorService.pesquisarPorRegiaoMunicipio(regiao);
        return historicoPrecoRepository.findAllByRevendedorIn(revendedores, pageRequest);
    }

    public Page<HistoricoPreco> pesquisarPorNomeDistribuidora(String nome,
                                                              Integer page,
                                                              Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<Revendedor> revendedores = revendedorService.pesquisarPorNome(nome);
        return historicoPrecoRepository.findAllByRevendedorIn(revendedores, pageRequest);
    }

    public Page<HistoricoPreco> pesquisarPorCnpjDistribuidora(String cnpj,
                                                              Integer page,
                                                              Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<Revendedor> revendedores = revendedorService.pesquisarPorCnpj(cnpj);
        return historicoPrecoRepository.findAllByRevendedorIn(revendedores, pageRequest);
    }


    public Page<HistoricoPreco> pesquisarPorDataColeta(String dataColeta,
                                                       Integer page,
                                                       Integer size) {
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            PageRequest pageRequest = PageRequest.of(page, size);
            return historicoPrecoRepository.findAllByDataColeta(LocalDate
                            .parse(dataColeta, dateTimeFormatter)
                    , pageRequest);
        } catch (DateTimeParseException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Informe a data no formato dd/mm/aaaa.");
        }
    }

    public ValorDTO calcularPrecoMedioCompraEVendaPorBandeira(String bandeira) {
        List<Revendedor> revendedores = revendedorService.pesquisarPorBandeira(bandeira);
        return calcularPrecoMedioCompraEVendaPorRevendedores(revendedores);
    }

    public ValorDTO calcularPrecoMedioCompraEVendaPorMunicipio(String municipio, EstadoEnum estado) {
        List<Revendedor> revendedores = revendedorService.pesquisarPorNomeEEstadoMunicipio(municipio, estado);
        return calcularPrecoMedioCompraEVendaPorRevendedores(revendedores);
    }

    public ValorDTO calcularPrecoMedioCompraEVendaPorRevendedores(List<Revendedor> revendedores) {
        Object[] resultado = historicoPrecoRepository.calcularPrecoMedioCompraEVendaPorRevendedores(revendedores);
        Object[] registro = (Object[]) resultado[0];
        return new ValorDTO((double) registro[0], (double) registro[1]);
    }
}
