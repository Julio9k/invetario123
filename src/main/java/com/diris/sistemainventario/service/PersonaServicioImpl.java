package com.diris.sistemainventario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diris.sistemainventario.model.Persona;
import com.diris.sistemainventario.repository.PersonaRespositorio;

@Service
public class PersonaServicioImpl implements PersonaServicio {

    @Autowired
    private PersonaRespositorio personaRespositorio;

    public PersonaServicioImpl(PersonaRespositorio personaRespositorio) {
        this.personaRespositorio = personaRespositorio;
    }

    @Override
    public void registrar(Persona persona) throws Exception {
        personaRespositorio.registrar(persona);
    }

    @Override
    public void modificar(Persona persona) throws Exception {
        personaRespositorio.modificar(persona);
    }

    @Override
    public void eliminar(int id_persona) throws Exception {
        personaRespositorio.eliminar(id_persona);
    }

    @Override
    public Persona obtenerPersona(int id_persona) throws Exception {
        return personaRespositorio.obtenerPersona(id_persona);
    }

    @Override
    public List<Persona> listar() throws Exception {
        return personaRespositorio.listar();
    }

}
