package com.diris.sistemainventario.repository;

import java.util.List;

import com.diris.sistemainventario.dto.DocumentoFiltro;
import com.diris.sistemainventario.model.Documento;

public interface DocumentoRepositorio {

    public void registrar(Documento documento) throws Exception;

    public void modificar(Documento documento) throws Exception;

    public void eliminar(int id_documento) throws Exception;

    public Documento obtenerDoc(int id_documento) throws Exception;

    public List<Documento> listar(DocumentoFiltro documentoFiltro) throws Exception;

    // Listar los datos de la BD a una Select en la vista
    public List<String> litaCodigo() throws Exception;

    public List<String> litaTipo() throws Exception;

    public List<String> litaDistrito() throws Exception;

    public List<String> litaTomo() throws Exception;

    public List<String> litaCaja() throws Exception;

    public List<String> litaEstado() throws Exception;

}
