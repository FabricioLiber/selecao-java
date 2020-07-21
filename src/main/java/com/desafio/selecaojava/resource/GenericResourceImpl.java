package com.desafio.selecaojava.resource;

import com.desafio.selecaojava.service.GenericService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class GenericResourceImpl<T> implements GenericResource<T> {

    protected final GenericService<T> genericService;

    public GenericResourceImpl(GenericService<T> genericService) {
        this.genericService = genericService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void adicionar(@RequestBody T obj) {
        this.genericService.adicionar(obj);
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<T> pesquisarPorId(@PathVariable Long id) {
        T obj = this.genericService
                .pesquisarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Objeto não encontrado."));
        return ResponseEntity.ok(obj);
    }

    @Override
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping
    public void atualizar(@RequestBody T obj) {
        this.genericService.atualizar(obj);
    }

    @Override
    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping(value = "/{id}")
    public void deletar(@PathVariable Long id) {
        this.genericService.deletar(id);
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/lista")
    public void adicionar(@RequestBody List<T> obj) {
        this.genericService.adicionar(obj);
    }

}
