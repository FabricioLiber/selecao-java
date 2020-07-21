package com.desafio.selecaojava.service;

import com.desafio.selecaojava.domain.*;
import com.desafio.selecaojava.domain.repository.HistoricoPrecoRepository;
import com.desafio.selecaojava.domain.repository.MunicipioRepository;
import com.desafio.selecaojava.domain.repository.ProdutoRepository;
import com.desafio.selecaojava.domain.repository.RevendedorRepository;
import com.desafio.selecaojava.exception.ExtracaoNaoFinalizadaException;
import com.desafio.selecaojava.exception.GravacaoDeDadosNaoFinalizadaException;
import com.desafio.selecaojava.service.csv.ArquivoDTO;
import com.desafio.selecaojava.service.csv.EmptyLineFilter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class ImportService {

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    private final MunicipioRepository municipioRepository;

    private final ProdutoRepository produtoRepository;

    private final RevendedorRepository revendedorRepository;

    private final HistoricoPrecoRepository historicoPrecoRepository;


    private final ForkJoinPool customThreadPool1 = new ForkJoinPool(4);
    private final ForkJoinPool customThreadPool2 = new ForkJoinPool(4);
    private final ForkJoinPool totalTheadPool = new ForkJoinPool(8);

    public ImportService(MunicipioRepository municipioRepository,
                         ProdutoRepository produtoRepository,
                         RevendedorRepository revendedorRepository,
                         HistoricoPrecoRepository historicoPrecoRepository) {
        this.municipioRepository = municipioRepository;
        this.produtoRepository = produtoRepository;
        this.revendedorRepository = revendedorRepository;
        this.historicoPrecoRepository = historicoPrecoRepository;
    }


    public void importarCSV(MultipartFile file) throws ExtracaoNaoFinalizadaException,
            GravacaoDeDadosNaoFinalizadaException {

        try {

            logger.info("Extração do CSV.");
            Reader reader = new InputStreamReader(file.getInputStream());

            CsvToBean<ArquivoDTO> csvToBean = new CsvToBeanBuilder<ArquivoDTO>(reader)
                    .withSeparator('\t')
                    .withType(ArquivoDTO.class)
                    .withSkipLines(1)
                    .withIgnoreEmptyLine(true)
                    .withFilter(new EmptyLineFilter())
                    .build();

            logger.info("Início da gravação de dados na base.");
            salvarDados(csvToBean.parse());
            logger.info("Extração concluída.");

        } catch (IOException exception) {
            throw new ExtracaoNaoFinalizadaException("Ocorreu um erro na extração de dados do CSV.");
        } catch (ExecutionException | InterruptedException exception) {
            throw new GravacaoDeDadosNaoFinalizadaException("Ocorreu um erro na gração dos dados extraídos.");
        }

    }

    private void salvarDados(List<ArquivoDTO> arquivoDTOList) throws ExecutionException, InterruptedException {

        Future<List<Municipio>> municipiosFuture = customThreadPool1.submit(() -> {

            Map<EstadoEnum, Map<String, List<Municipio>>> municipiosPorEstadoENome = arquivoDTOList
                    .parallelStream()
                    .map(this::criarMunicipio)
                    .collect(Collectors.groupingBy(Municipio::getEstado, Collectors.groupingBy(Municipio::getNome)));

            List<Municipio> municipios = new ArrayList<>();
            municipiosPorEstadoENome
                    .values()
                    .forEach(municipiosPorNome -> municipios.addAll(municipiosPorNome
                            .values()
                            .parallelStream()
                            .map(municipio -> municipio.get(0))
                            .collect(Collectors.toList())));

            municipioRepository.saveAll(municipios);
            logger.info(municipios.size() + " Municípios cadastrados.");
            return municipios;
        });

        Future<List<Produto>> produtosFuture = customThreadPool2.submit(() -> {
            Map<String, List<Produto>> produtosMap = arquivoDTOList
                    .parallelStream()
                    .map(this::criarProduto)
                    .collect(Collectors.groupingBy(Produto::getNome));

            List<Produto> produtos = produtosMap
                    .values()
                    .parallelStream()
                    .map(produtoList -> produtoList.get(0)).collect(Collectors.toList());

            produtoRepository.saveAll(produtos);
            logger.info(produtos.size() + " Produtos cadastrados.");
            return produtos;
        });

        final List<Municipio> municipios = municipiosFuture.get();

        Future<List<Revendedor>> revendedoresFuture = customThreadPool1.submit(() -> {
            Map<String, List<Revendedor>> revendedoresMap = arquivoDTOList
                    .parallelStream()
                    .map(arquivoDTO -> this.criarRevendedor(arquivoDTO, municipios))
                    .collect(Collectors.groupingBy(Revendedor::getCnpj));

            List<Revendedor> revendedores = revendedoresMap
                    .values()
                    .parallelStream()
                    .map(produtoList -> produtoList.get(0)).collect(Collectors.toList());


            revendedorRepository.saveAll(revendedores);
            logger.info(revendedores.size() + " Revendedores cadastrados.");
            return revendedores;
        });

        List<Produto> produtos = produtosFuture.get();

        List<Revendedor> revendedores = revendedoresFuture.get();


        totalTheadPool.submit(() -> {
            List<HistoricoPreco> historicoPrecos = arquivoDTOList
                    .parallelStream()
                    .map(arquivoDTO -> this.criarColeta(arquivoDTO, produtos, revendedores))
                    .collect(Collectors.toList());

            historicoPrecoRepository.saveAll(historicoPrecos);
            logger.info(historicoPrecos.size() + " Históricos de preço cadastrados.");
        }).get();
    }

    private Produto criarProduto(ArquivoDTO arquivoDTO) {

        return new Produto(arquivoDTO.getProduto(),
                UnidadeMedidaEnum.get(arquivoDTO.getUnidadeMedida()));
    }

    private Municipio criarMunicipio(ArquivoDTO arquivoDTO) {
        return new Municipio(arquivoDTO.getMunicipio(),
                EstadoEnum.valueOf(arquivoDTO.getEstado()),
                RegiaoEnum.valueOf(arquivoDTO.getRegiao()));
    }

    private Revendedor criarRevendedor(ArquivoDTO arquivoDTO,
                                       List<Municipio> municipios) {

        return new Revendedor(arquivoDTO.getRevenda(),
                arquivoDTO.getCnpj(),
                arquivoDTO.getBandeira(),
                municipios
                        .stream()
                        .filter(municipio -> municipio
                                .getNome()
                                .equals(arquivoDTO.getMunicipio()))
                        .findFirst()
                        .orElseThrow());

    }

    private HistoricoPreco criarColeta(ArquivoDTO arquivoDTO,
                                       List<Produto> produtos,
                                       List<Revendedor> revendedores) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Produto produto = produtos
                .stream()
                .filter(item -> item
                        .getNome()
                        .equals(arquivoDTO.getProduto()))
                .findFirst()
                .orElseThrow();

        Revendedor revendedor = revendedores
                .stream()
                .filter(item -> item
                        .getCnpj()
                        .equals(arquivoDTO.getCnpj()))
                .findFirst()
                .orElseThrow();

        Double valorCompra = arquivoDTO
                .getValorCompra() != null
                && arquivoDTO
                .getValorCompra()
                .length() > 0 ? Double.valueOf(arquivoDTO.getValorVenda().replace(",", ".")) : null;

        return HistoricoPreco
                .builder()
                .produto(produto)
                .revendedor(revendedor)
                .valorVenda(Double.valueOf(arquivoDTO.getValorVenda().replace(",", ".")))
                .valorCompra(valorCompra)
                .dataColeta(LocalDate.parse(arquivoDTO.getDataColeta(), dateTimeFormatter))
                .build();
    }
}
