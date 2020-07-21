package com.desafio.selecaojava.resource;

import com.desafio.selecaojava.domain.HistoricoPreco;
import com.desafio.selecaojava.exception.ExtracaoNaoFinalizadaException;
import com.desafio.selecaojava.exception.GravacaoDeDadosNaoFinalizadaException;
import com.desafio.selecaojava.resource.dto.ValorDTO;
import com.desafio.selecaojava.service.HistoricoPrecoService;
import com.desafio.selecaojava.service.ImportService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/historicos")
public class HistoricoPrecoResource extends GenericResourceImpl<HistoricoPreco> {

    private final HistoricoPrecoService historicoPrecoService = ((HistoricoPrecoService) this.genericService);


    public HistoricoPrecoResource(HistoricoPrecoService historicoPrecoService) {
        super(historicoPrecoService);
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/municipio/preco-medio")
    public ResponseEntity<Double> calcularPrecoMedioPorMunicipio(@RequestParam("municipio") String municipio) {
        Double valor = historicoPrecoService.calcularMedioPrecoPorMunicipio(municipio);
        return ResponseEntity.ok(valor);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/regiao")
    public ResponseEntity<Page<HistoricoPreco>> pesquisarPorRegiao(@RequestParam("regiao") String regiao,
                                                                   @RequestParam("page") int page,
                                                                   @RequestParam("size") int size) {
        Page<HistoricoPreco> historicos = historicoPrecoService.pesquisarPorRegiao(regiao,
                page,
                size);
        return ResponseEntity.ok(historicos);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/distribuidora/nome")
    public ResponseEntity<Page<HistoricoPreco>> pesquisarPorNomeDistribuidora(@RequestParam("distribuidora") String distribuidora,
                                                                              @RequestParam("page") int page,
                                                                              @RequestParam("size") int size) {
        Page<HistoricoPreco> historicos = historicoPrecoService
                .pesquisarPorNomeDistribuidora(distribuidora,
                        page,
                        size);
        return ResponseEntity.ok(historicos);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/distribuidora/cnpj")
    public ResponseEntity<Page<HistoricoPreco>> pesquisarPorCnpjDistribuidora(@RequestParam("cnpj") String cnpj,
                                                                              @RequestParam("page") int page,
                                                                              @RequestParam("size") int size) {
        Page<HistoricoPreco> historicos = historicoPrecoService
                .pesquisarPorCnpjDistribuidora(cnpj,
                        page,
                        size);
        return ResponseEntity.ok(historicos);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/data-coleta")
    public ResponseEntity<Page<HistoricoPreco>> pesquisarPorDataColeta(@RequestParam("dataColeta") String dataColeta,
                                                                       @RequestParam("page") int page,
                                                                       @RequestParam("size") int size) {

        Page<HistoricoPreco> historicos = historicoPrecoService
                .pesquisarPorDataColeta(dataColeta,
                        page,
                        size);
        return ResponseEntity.ok(historicos);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/municipio/compra-venda")
    public ResponseEntity<ValorDTO> calcularPrecoMedioCompraEVendaPorMunicipio(@RequestParam("municipio") String municipio) {
        ValorDTO valorDTO = historicoPrecoService.calcularPrecoMedioCompraEVendaPorMunicipio(municipio);
        return ResponseEntity.ok(valorDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/bandeira/compra-venda")
    public ResponseEntity<ValorDTO> calcularPrecoMedioCompraEVendaPorBandeira(@RequestParam("bandeira") String bandeira) {
        ValorDTO valorDTO = historicoPrecoService.calcularPrecoMedioCompraEVendaPorBandeira(bandeira);
        return ResponseEntity.ok(valorDTO);
    }

}
