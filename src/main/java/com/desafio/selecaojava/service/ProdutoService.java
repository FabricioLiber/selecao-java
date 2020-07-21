package com.desafio.selecaojava.service;

import com.desafio.selecaojava.domain.Produto;
import com.desafio.selecaojava.domain.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService extends GenericServiceImpl<Produto> {

    public ProdutoService(ProdutoRepository produtoRepository) {
        super(produtoRepository);
    }

}
