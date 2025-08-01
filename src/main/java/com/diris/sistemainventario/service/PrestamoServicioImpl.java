package com.diris.sistemainventario.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diris.sistemainventario.model.Prestamo;
import com.diris.sistemainventario.repository.PrestamoRepositorio;

@Service
public class PrestamoServicioImpl implements PrestamoServicio {

    @Autowired
    private PrestamoRepositorio prestamoRepositorio;

    @Override
    public void registrar(Prestamo prestamo) throws Exception {
        prestamoRepositorio.registrar(prestamo);
    }

    @Override
    public void modificar(Prestamo prestamo) throws Exception {
        prestamoRepositorio.modificar(prestamo);
    }

    @Override
    public void eliminar(int idPrestamo) throws Exception {
        prestamoRepositorio.eliminar(idPrestamo);
    }

    @Override
    public Prestamo obtener(int idPrestamo) throws Exception {
        return prestamoRepositorio.obtener(idPrestamo);
    }

    @Override
    public List<Prestamo> listar() throws Exception {
        return prestamoRepositorio.listar();
    }

    @Override
    public List<Prestamo> listarPorPersona(int idPersona) throws Exception {
        return prestamoRepositorio.listarPorPersona(idPersona);
    }

    @Override
    public void registrarDevolucion(int idPrestamo, Date fechaDevolucion) throws Exception {
        prestamoRepositorio.registrarDevolucion(idPrestamo, fechaDevolucion);
    }

    @Override
    public List<String> listaDocumentos() throws Exception {
        return prestamoRepositorio.listaDocumentos();
    }

    @Override
    public List<String> listaPersonas() throws Exception {
        return prestamoRepositorio.listaPersonas();
    }

}
