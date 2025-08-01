package com.diris.sistemainventario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.diris.sistemainventario.dto.DocumentoFiltro;
import com.diris.sistemainventario.model.Documento;
import com.diris.sistemainventario.service.DocumentoServicio;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/documento")
@Controller
public class DocumentoControlador {

    @Autowired
    private DocumentoServicio documentoServicio;

    @GetMapping("/BuscarDocumento")
    public String mostrarBusqueda(Model model) throws Exception {
        model.addAttribute("documentoFiltro", new DocumentoFiltro());
        cargarDatosComunes(model);
        return "BuscarDocumento";
    }

    @PostMapping("/filtrarDocumento")
    public String filtrarDocumento(@ModelAttribute DocumentoFiltro documentoFiltro, Model model) throws Exception {
        model.addAttribute("documentoFiltro", documentoFiltro);
        model.addAttribute("listaDocumento", documentoServicio.listar(documentoFiltro));
        cargarDatosComunes(model);
        return "BuscarDocumento";
    }

    @GetMapping("/RegistrarDocumento")
    public String mostrarFormularioNuevo(Model model) throws Exception {
        model.addAttribute("documento", new Documento());
        cargarDatosCompletos(model);
        return "RegistrarDocumento"; // nombre del archivo HTML (registroDocumento.html)
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Documento documento) throws Exception {
        if (documento.getIdDocumento() == null) {
            documentoServicio.registrar(documento);
            return "redirect:/documento/RegistrarDocumento";
        } else {
            documentoServicio.modificar(documento);
            return "redirect:/documento/BuscarDocumento";
        }
    }

    @GetMapping("/editarDocumento/{id}")
    public String mostrarFormularioEditar(@PathVariable int id, Model model) throws Exception {
        cargarDatosCompletos(model);
        model.addAttribute("documento", documentoServicio.obtenerDoc(id));
        return "RegistrarDocumento";
    }

    @GetMapping("eliminarDocumento/{id}")
    public String eliminarDocumento(@PathVariable int id) throws Exception {
        documentoServicio.eliminar(id);
        return "redirect:/documento/BuscarDocumento";
    }

    private void cargarDatosComunes(Model model) throws Exception {
        model.addAttribute("actas", documentoServicio.litaCodigo());
        model.addAttribute("tipos", documentoServicio.litaTipo());
        model.addAttribute("distritos", documentoServicio.litaDistrito());
    }

    private void cargarDatosCompletos(Model model) throws Exception {
        cargarDatosComunes(model);
        model.addAttribute("cajas", documentoServicio.litaCaja());
        model.addAttribute("tomos", documentoServicio.litaTomo());
        model.addAttribute("estados", documentoServicio.litaEstado());
    }
}
