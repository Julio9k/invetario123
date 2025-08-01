package com.diris.sistemainventario.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.diris.sistemainventario.model.Usuario;

@Repository
public class UsuarioRespositorioImpl implements UsuarioRespositorio{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean validarUsuario(Usuario usuario) {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE nombre = ? AND contrasena = ?";
        Integer count = jdbcTemplate.queryForObject(
                sql,
                Integer.class,
                usuario.getNombreUsuario(),
                usuario.getContrasenia()
        );
        return (count != null && count > 0);
    }
}
