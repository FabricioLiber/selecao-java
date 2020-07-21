package com.desafio.selecaojava.resource;

import com.desafio.selecaojava.domain.Revendedor;
import com.desafio.selecaojava.service.RevendedorService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "revendedores")
public class RevendedorResource extends GenericResourceImpl<Revendedor> {

    public RevendedorResource(RevendedorService revendedorService) {
        super(revendedorService);
    }

}
