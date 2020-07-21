package com.desafio.selecaojava.resource;

import com.desafio.selecaojava.domain.Municipio;
import com.desafio.selecaojava.service.MunicipioService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "municipios")
public class MunicipioResource extends GenericResourceImpl<Municipio> {

    public MunicipioResource(MunicipioService municipioService) {
        super(municipioService);
    }
}
