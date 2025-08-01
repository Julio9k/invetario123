package com.diris.sistemainventario.controller;

import com.diris.sistemainventario.model.Usuario;
import com.diris.sistemainventario.service.UsuarioServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/login")
    public String mostrarLogin(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "login";
    }

    @PostMapping("/validarLogin")
    public String validarLogin(@ModelAttribute("usuario") Usuario usuario, Model model) {
        if (usuarioServicio.validar(usuario)) {
            return "redirect:/documento/BuscarDocumento"; 
        } else {
            model.addAttribute("error", "Credenciales inválidas");
            return "login";
        }
    }
}
