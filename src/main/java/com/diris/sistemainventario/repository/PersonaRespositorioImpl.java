package com.diris.sistemainventario.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.diris.sistemainventario.model.Persona;

@Repository
public class PersonaRespositorioImpl implements PersonaRespositorio {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void registrar(Persona persona) throws Exception {
        String sql = "CALL sp_registrar_persona(?, ?, ?)";
        jdbcTemplate.update(sql,
                persona.getNombre(),
                persona.getApellido(),
                persona.getCorreo());
    }

    @Override
    public void modificar(Persona persona) throws Exception {
        String sql = "CALL sp_modificar_persona(?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                persona.getIdPersona(),
                persona.getNombre(),
                persona.getApellido(),
                persona.getCorreo());
    }

    @Override
    public void eliminar(int id_persona) throws Exception {
        String sql = "CALL sp_eliminar_persona(?)";
        jdbcTemplate.update(sql, id_persona);
    }

    @Override
    public Persona obtenerPersona(int id_persona) throws Exception {
        String sql = "SELECT * FROM personas WHERE id_persona = ? LIMIT 1";
        return jdbcTemplate.queryForObject(sql, new Object[] { id_persona }, personaRowMapper());
    }

    @Override
    public List<Persona> listar() throws Exception {
        String sql = "SELECT * FROM personas";
        return jdbcTemplate.query(sql, personaRowMapper());
    }

    private RowMapper<Persona> personaRowMapper() {
        return (rs, rowNum) -> {
            Persona p = new Persona();
            p.setIdPersona(rs.getInt("id_persona"));
            p.setNombre(rs.getString("nombre"));
            p.setApellido(rs.getString("apellido"));
            p.setCorreo(rs.getString("correo"));
            return p;
        };
    }

}
