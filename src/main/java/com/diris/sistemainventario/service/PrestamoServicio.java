package com.diris.sistemainventario.service;

import java.sql.Date;
import java.util.List;

import com.diris.sistemainventario.model.Prestamo;

public interface PrestamoServicio {

    void registrar(Prestamo prestamo) throws Exception;

    void modificar(Prestamo prestamo) throws Exception;

    void eliminar(int idPrestamo) throws Exception;

    Prestamo obtener(int idPrestamo) throws Exception;

    List<Prestamo> listar() throws Exception;

    List<Prestamo> listarPorPersona(int idPersona) throws Exception;

    void registrarDevolucion(int idPrestamo, Date fechaDevolucion) throws Exception;

    List<String> listaDocumentos() throws Exception;

    List<String> listaPersonas() throws Exception;
}
