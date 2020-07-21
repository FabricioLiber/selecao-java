package com.desafio.selecaojava.service;

import com.desafio.selecaojava.domain.repository.GenericRepository;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

public class GenericServiceImpl<T> implements GenericService<T> {

    protected final GenericRepository<T> genericRepository;

    public GenericServiceImpl(GenericRepository<T> genericRepository) {
        this.genericRepository = genericRepository;
    }

    @Override
    public void adicionar(List<T> lista) {
        this.genericRepository.saveAll(lista);
    }

    @Override
    public void adicionar(T obj) {
        this.genericRepository.save(obj);
    }

    @Override
    public Optional<T> pesquisarPorId(Long id) {
        return this.genericRepository.findById(id);
    }

    @Override
    public void atualizar(T obj) {
        this.genericRepository.save(obj);
    }

    @Override
    public void deletar(Long id) {
        this.genericRepository.deleteById(id);
    }
}
