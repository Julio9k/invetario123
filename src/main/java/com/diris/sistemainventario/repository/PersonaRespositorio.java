package com.diris.sistemainventario.repository;

import java.util.List;

import com.diris.sistemainventario.model.Persona;

public interface PersonaRespositorio {

    void registrar(Persona persona) throws Exception;

    void modificar(Persona persona) throws Exception;

    void eliminar(int id_persona) throws Exception;

    Persona obtenerPersona(int id_persona) throws Exception;

    List<Persona> listar() throws Exception;

}
