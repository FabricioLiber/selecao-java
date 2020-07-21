package com.desafio.selecaojava.domain.repository;

import com.desafio.selecaojava.domain.HistoricoPreco;
import com.desafio.selecaojava.domain.Revendedor;
import com.desafio.selecaojava.resource.dto.ValorDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface HistoricoPrecoRepository extends GenericRepository<HistoricoPreco> {

    List<HistoricoPreco> findAllByRevendedorIn(List<Revendedor> revendedores);

    Page<HistoricoPreco> findAllByRevendedorIn(List<Revendedor> revendedores,
                                               PageRequest pageRequest);

    Page<HistoricoPreco> findAllByDataColeta(LocalDate dataColeta,
                                             PageRequest pageRequest);

    @Query(value = "select avg(hp.valorCompra) as valorCompra, avg(hp.valorVenda) as valorVenda from historico_preco hp where hp.revendedor in :revendedores")
    ValorDTO calcularPrecoMedioCompraEVendaPorRevendedores(List<Revendedor> revendedores);
}
