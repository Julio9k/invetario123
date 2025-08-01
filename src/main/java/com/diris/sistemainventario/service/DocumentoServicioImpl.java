package com.diris.sistemainventario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diris.sistemainventario.dto.DocumentoFiltro;
import com.diris.sistemainventario.model.Documento;
import com.diris.sistemainventario.repository.DocumentoRepositorio;

@Service
public class DocumentoServicioImpl implements DocumentoServicio {

    @Autowired
    DocumentoRepositorio documentoRepositorio;

    @Override
    public void registrar(Documento documento) throws Exception {
        documentoRepositorio.registrar(documento);
    }

    @Override
    public void modificar(Documento documento) throws Exception {
        documentoRepositorio.modificar(documento);
    }

    @Override
    public void eliminar(int id_codigo) throws Exception {
        documentoRepositorio.eliminar(id_codigo);
    }

    @Override
    public List<Documento> listar(DocumentoFiltro documentoFiltro) throws Exception {
        return documentoRepositorio.listar(documentoFiltro);
    }

    @Override
    public List<String> litaCodigo() throws Exception {
        return documentoRepositorio.litaCodigo();
    }

    @Override
    public List<String> litaTipo() throws Exception {
        return documentoRepositorio.litaTipo();
    }

    @Override
    public List<String> litaDistrito() throws Exception {
        return documentoRepositorio.litaDistrito();
    }

    @Override
    public List<String> litaTomo() throws Exception {
        return documentoRepositorio.litaTomo();
    }

    @Override
    public List<String> litaCaja() throws Exception {
        return documentoRepositorio.litaCaja();
    }

    @Override
    public List<String> litaEstado() throws Exception {
        return documentoRepositorio.litaEstado();
    }

    @Override
    public Documento obtenerDoc(int id_documento) throws Exception {
        return documentoRepositorio.obtenerDoc(id_documento);
    }
}
