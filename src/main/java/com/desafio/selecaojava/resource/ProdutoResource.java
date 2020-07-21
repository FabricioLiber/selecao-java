package com.desafio.selecaojava.resource;

import com.desafio.selecaojava.domain.Produto;
import com.desafio.selecaojava.service.GenericService;
import com.desafio.selecaojava.service.ProdutoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "produtos")
public class ProdutoResource extends GenericResourceImpl<Produto> {

    public ProdutoResource(ProdutoService produtoService) {
        super(produtoService);
    }
}
