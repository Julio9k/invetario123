package com.diris.sistemainventario.service;

import com.diris.sistemainventario.model.Usuario;
import com.diris.sistemainventario.repository.UsuarioRespositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicioImpl implements UsuarioServicio{

    @Autowired
    private UsuarioRespositorio usuarioRepositorio;

    @Override
    public boolean validar(Usuario usuario) {

        return usuarioRepositorio.validarUsuario(usuario);
    }
}
