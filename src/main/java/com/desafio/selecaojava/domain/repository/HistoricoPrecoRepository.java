package com.desafio.selecaojava.domain.repository;

import com.desafio.selecaojava.domain.HistoricoPreco;
import com.desafio.selecaojava.domain.Revendedor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.List;

public interface HistoricoPrecoRepository extends GenericRepository<HistoricoPreco> {

    List<HistoricoPreco> findAllByRevendedorIn(List<Revendedor> revendedores);

    Page<HistoricoPreco> findAllByRevendedorIn(List<Revendedor> revendedores,
                                               PageRequest pageRequest);

    Page<HistoricoPreco> findAllByDataColeta(LocalDate dataColeta,
                                             PageRequest pageRequest);
}
