package com.diris.sistemainventario.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.diris.sistemainventario.model.Documento;
import com.diris.sistemainventario.model.Persona;
import com.diris.sistemainventario.model.Prestamo;

@Repository
public class PrestamoRepositorioImpl implements PrestamoRepositorio {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void registrar(Prestamo prestamo) throws Exception {
        String sql = "CALL sp_registrar_prestamo(?, ?, ?)";
        jdbcTemplate.update(sql,
                prestamo.getDocumento().getIdDocumento(),
                prestamo.getPersona().getIdPersona(),
                prestamo.getFechaPrestamo());
    }

    @Override
    public void modificar(Prestamo prestamo) throws Exception {
        String sql = "CALL sp_modificar_prestamo(?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                prestamo.getIdPrestamo(),
                prestamo.getDocumento().getIdDocumento(),
                prestamo.getPersona().getIdPersona(),
                prestamo.getFechaPrestamo(),
                prestamo.getFechaDevolucion());
    }

    @Override
    public void eliminar(int idPrestamo) throws Exception {
        String sql = "CALL sp_eliminar_prestamo(?)";
        jdbcTemplate.update(sql, idPrestamo);
    }

    @SuppressWarnings("deprecation")
	@Override
    public Prestamo obtener(int idPrestamo) throws Exception {
        String sql = "SELECT * FROM view_PrestamoData WHERE idPrestamo = ? LIMIT 1";
        return jdbcTemplate.queryForObject(sql, new Object[] { idPrestamo }, prestamoRowMapper());
    }

    @Override
    public List<Prestamo> listar() throws Exception {
        String sql = "SELECT * FROM view_PrestamoData";
        return jdbcTemplate.query(sql, prestamoRowMapper());
    }

    @SuppressWarnings("deprecation")
	@Override
    public List<Prestamo> listarPorPersona(int idPersona) throws Exception {
        String sql = "SELECT * FROM view_PrestamoData WHERE idPersona = ?";
        return jdbcTemplate.query(sql, new Object[] { idPersona }, prestamoRowMapper());
    }

    @Override
    public void registrarDevolucion(int idPrestamo, Date fechaDevolucion) throws Exception {
        String sql = "CALL sp_registrar_devolucion(?, ?)";
        jdbcTemplate.update(sql,
                idPrestamo,
                fechaDevolucion);
    }

    private RowMapper<Prestamo> prestamoRowMapper() {
        return (rs, rowNum) -> {
            Documento documento = new Documento();
            documento.setIdDocumento(rs.getInt("idDocumento"));
            documento.setNombreDocumento(rs.getString("nombreDocumento"));
            documento.setEstado(rs.getString("estadoDocumento"));

            Persona persona = new Persona();
            persona.setIdPersona(rs.getInt("idPersona"));
            persona.setNombre(rs.getString("nombrePersona"));
            persona.setApellido(rs.getString("apellidoPersona"));
            persona.setCorreo(rs.getString("correoPersona"));

            Prestamo p = new Prestamo();
            p.setIdPrestamo(rs.getInt("idPrestamo"));
            p.setPersona(persona);
            p.setDocumento(documento);
            p.setFechaPrestamo(rs.getDate("fechaPrestamo"));
            p.setFechaDevolucion(rs.getDate("fechaRegreso"));
            return p;
        };
    }

    @Override
    public List<String> listaDocumentos() throws Exception {
        return listarNombre("documentos");
    }

    @Override
    public List<String> listaPersonas() throws Exception {
        return listarNombre("personas");
    }

    private List<String> listarNombre(String tabla) {
        String sql = ("SELECT nombre FROM " + tabla).toString();
        return jdbcTemplate.queryForList(sql, String.class);
    }
}
