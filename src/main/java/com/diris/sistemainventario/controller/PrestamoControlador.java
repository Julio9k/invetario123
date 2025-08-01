package com.diris.sistemainventario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.diris.sistemainventario.model.Prestamo;
import com.diris.sistemainventario.service.PrestamoServicio;

@RequestMapping("/prestamo")
@Controller
public class PrestamoControlador {

    @Autowired
    private PrestamoServicio prestamoServicio;

    @GetMapping("/RegistrarPrestamos")
    public String vista_RegistrarPrestamo(Model model) throws Exception {
        model.addAttribute("prestamo", new Prestamo());
        model.addAttribute("listaPrestamos", prestamoServicio.listar());
        return "RegistrarPrestamos";
    }

    @PostMapping("/guardar")
    public String registrarPrestamo(@ModelAttribute Prestamo prestamo) throws Exception {
        if (prestamo.getIdPrestamo() == null) {
            prestamoServicio.registrar(prestamo);
            return "redirect:/prestamo/RegistrarPrestamos";
        } else {
            prestamoServicio.modificar(prestamo);
            return "redirect:/prestamo/RegistrarPrestamos";
        }
    }

    @GetMapping("/editarPrestamo/{id}")
    public String editarPrestamo(Integer id, Model model) throws Exception {
        model.addAttribute("prestamo", prestamoServicio.obtener(id));
        model.addAttribute("listaPrestamos", prestamoServicio.listar());
        return "RegistrarPrestamos";
    }

    @GetMapping("/eliminarPrestamo/{idPrestamo}")
    public String eliminarPrestamo(Integer idPrestamo) throws Exception {
        prestamoServicio.eliminar(idPrestamo);
        return "redirect:/prestamo/RegistrarPrestamos";
    }
}
