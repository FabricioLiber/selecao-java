package com.desafio.selecaojava.resource;

import com.desafio.selecaojava.domain.Usuario;
import com.desafio.selecaojava.service.UsuarioService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "usuarios")
public class UsuarioResource extends GenericResourceImpl<Usuario> {

    public UsuarioResource(UsuarioService usuarioService) {
        super(usuarioService);
    }

}
