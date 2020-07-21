package com.desafio.selecaojava.service;

import com.desafio.selecaojava.domain.Usuario;
import com.desafio.selecaojava.domain.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService extends GenericServiceImpl<Usuario> {

    public UsuarioService(UsuarioRepository usuarioRepository) {
        super(usuarioRepository);
    }
}
