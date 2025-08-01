package com.diris.sistemainventario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.diris.sistemainventario.model.Persona;
import com.diris.sistemainventario.service.PersonaServicio;

@RequestMapping("/persona")
@Controller
public class PersonaControlador {

    @Autowired
    private PersonaServicio personaServicio;

    @GetMapping("/RegistrarPersona")
    public String vista_RegistrarPersona(Model model) throws Exception {
        model.addAttribute("persona", new Persona());
        model.addAttribute("listaPersonas", personaServicio.listar());
        return "RegistrarPersona";
    }

    @PostMapping("/guardar")
    public String registrarPersona(@ModelAttribute Persona persona) throws Exception {
        if (persona.getIdPersona() == null) {
            personaServicio.registrar(persona);
            return "redirect:/persona/RegistrarPersona";
        } else {
            personaServicio.modificar(persona);
            return "redirect:/persona/RegistrarPersona";
        }
    }

    @GetMapping("/editarPersona/{id}")
    public String editarPersona(@PathVariable Integer id, Model model) throws Exception {
        model.addAttribute("persona", personaServicio.obtenerPersona(id));
        model.addAttribute("listaPersonas", personaServicio.listar());
        return "RegistrarPersona";
    }

    @GetMapping("/eliminarPersona/{idPersona}")
    public String eliminarPersona(@PathVariable Integer idPersona) throws Exception {
        personaServicio.eliminar(idPersona);
        return "redirect:/persona/RegistrarPersona";
    }
}
