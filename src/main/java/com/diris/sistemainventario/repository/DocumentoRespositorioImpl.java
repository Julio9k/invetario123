package com.diris.sistemainventario.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.diris.sistemainventario.dto.DocumentoFiltro;
import com.diris.sistemainventario.model.Documento;

import org.springframework.jdbc.core.RowMapper;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DocumentoRespositorioImpl implements DocumentoRepositorio {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void registrar(Documento documento) throws Exception {
    	
    	  if (documento.getNumActa() != null && !documento.getNumActa().trim().isEmpty()) {
    	        documento.setNumActa("ACTA " + documento.getNumActa().trim());
    	    }
    	  
    	  if (documento.getTomo() != null && !documento.getTomo().trim().isEmpty()) {
  	        documento.setTomo("TOMO " + documento.getTomo().trim());
  	    }
    	  
    	  if (documento.getCaja() != null && !documento.getCaja().trim().isEmpty()) {
  	        documento.setCaja("CAJA " + documento.getCaja().trim());
  	    }
    	  
        String sql = "SELECT sp_registrar_documento(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.queryForObject(sql, Object.class,
                documento.getNombreDocumento(),
                documento.getNumActa(),
                documento.getTipo(),
                documento.getDistrito(),
                documento.getDireccion(),
                documento.getTomo(),
                documento.getTomo(),
                documento.getEstado(),
                documento.getFechaRegistro(),
                documento.getAnio(),
                documento.getNumberExpediente(),
                documento.getResolucionAdmin()
        );
    }
    @Override
    public void modificar(Documento documento) throws Exception {
    	 String sql = "SELECT sp_modificar_documento(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    	    jdbcTemplate.queryForObject(sql, Object.class,
    	            documento.getIdDocumento(),
    	            documento.getNombreDocumento(),
    	            documento.getNumActa(),
    	            documento.getTipo(),
    	            documento.getDistrito(),
    	            documento.getDireccion(),
    	            documento.getTomo(),
    	            documento.getCaja(),
    	            documento.getEstado(),
    	            documento.getFechaRegistro(),
    	            documento.getAnio(),
    	            documento.getNumberExpediente(),
    	            documento.getResolucionAdmin()
    	    );
    	}
    @Override
    public void eliminar(int id_documento) throws Exception {
        String sql = "SELECT sp_eliminar_documento(?)";
        jdbcTemplate.queryForObject(sql, Object.class, id_documento);
    }
    @SuppressWarnings("deprecation")
	@Override
    public List<Documento> listar(DocumentoFiltro documentoFiltro) throws Exception {
        StringBuilder sql = new StringBuilder("SELECT * FROM view_DocumentoData WHERE 1=1");
        StringBuilder whereClause = new StringBuilder();
        List<Object> params = new ArrayList<>();

        if (documentoFiltro.getNombre() != null && !documentoFiltro.getNombre().trim().isEmpty()) {
            whereClause.append(" AND nombreDocumento = ?");
            params.add(documentoFiltro.getNombre().trim());
        }

        if (documentoFiltro.getNumActa() != null && !documentoFiltro.getNumActa().trim().isEmpty()) {
            whereClause.append(" AND numActa LIKE ?");
            params.add("%" + documentoFiltro.getNumActa().trim());
        }

        if (documentoFiltro.getAnio() != null) {
            whereClause.append(" AND anio = ?");
            params.add(documentoFiltro.getAnio());
        }

        if (documentoFiltro.getTipo() != null && !documentoFiltro.getTipo().trim().isEmpty()) {
            whereClause.append(" AND tipo = ?");
            params.add(documentoFiltro.getTipo());
        }

        if (documentoFiltro.getDistrito() != null && !documentoFiltro.getDistrito().trim().isEmpty()) {
            whereClause.append(" AND distrito = ?");
            params.add(documentoFiltro.getDistrito());
        }

        if (documentoFiltro.getResolAdmin() != null && !documentoFiltro.getResolAdmin().trim().isEmpty()) {
            whereClause.append(" AND resolAdmin = ?");
            params.add(documentoFiltro.getResolAdmin());
        }

        if (documentoFiltro.getNumExpediente() != null && !documentoFiltro.getNumExpediente().trim().isEmpty()) {
            whereClause.append(" AND numExpediente = ?");
            params.add(documentoFiltro.getNumExpediente());
        }

        sql.append(whereClause);
        return jdbcTemplate.query(sql.toString(), params.toArray(), documentoRowMapper());

    }

    @SuppressWarnings("deprecation")
	@Override
    public Documento obtenerDoc(int id_documento) throws Exception {
        String sql = "SELECT * FROM view_DocumentoData WHERE ID = ? LIMIT 1";
        return jdbcTemplate.queryForObject(sql, new Object[] { id_documento }, documentoRowMapper());
    }

    @Override
    public List<String> litaCodigo() throws Exception {
        return listarNombre("codigos");
    }

    @Override
    public List<String> litaTipo() throws Exception {
        return listarNombre("tipos");
    }

    @Override
    public List<String> litaDistrito() throws Exception {
        return listarNombre("distritos");
    }

    @Override
    public List<String> litaTomo() throws Exception {
        return listarNombre("tomos");
    }

    @Override
    public List<String> litaCaja() throws Exception {
        return listarNombre("cajas");
    }

    @Override
    public List<String> litaEstado() throws Exception {
        return listarNombre("estados");
    }

    private RowMapper<Documento> documentoRowMapper() {
        return (rs, rowNum) -> {
            Documento d = new Documento();
            d.setIdDocumento(rs.getInt("ID"));
            d.setNombreDocumento(rs.getString("nombreDocumento"));
            d.setNumActa(rs.getString("numActa"));
            d.setTipo(rs.getString("tipo"));
            d.setDistrito(rs.getString("distrito"));
            d.setDireccion(rs.getString("direccion"));
            d.setTomo(rs.getString("tomo"));
            d.setCaja(rs.getString("caja"));
            d.setEstado(rs.getString("estado"));
            d.setFechaRegistro((rs.getDate("fechaRegistro")));
            d.setAnio(rs.getInt("anio"));
            d.setNumberExpediente(rs.getString("numExpediente"));
            d.setResolucionAdmin(rs.getString("resolAdmin"));

            return d;
        };
    }

    private List<String> listarNombre(String tabla) {
        String sql = ("SELECT nombre FROM " + tabla).toString();
        return jdbcTemplate.queryForList(sql, String.class);
    }

}
