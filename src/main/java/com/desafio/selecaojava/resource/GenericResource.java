package com.desafio.selecaojava.resource;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface GenericResource<T> {

    void adicionar(List<T> lista);
    void adicionar(T obj);
    ResponseEntity<T> pesquisarPorId(Long id);
    void atualizar(T obj);
    void deletar(Long id);

}
