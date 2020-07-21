package com.desafio.selecaojava.service;

import java.util.List;
import java.util.Optional;

public interface GenericService<T> {

    void adicionar(List<T> lista);
    void adicionar(T obj);
    Optional<T> pesquisarPorId(Long id);
    void atualizar(T obj);
    void deletar(Long id);


}
