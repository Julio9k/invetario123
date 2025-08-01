package com.diris.sistemainventario.dto;

public class DocumentoFiltro {
    private String nombre;
    private String numActa;
    private Integer anio;
    private String tipo;
    private String distrito;
    private String resolAdmin;
    private String numExpediente;

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Integer getAnio() {
        return anio;
    }
    public void setAnio(Integer anio) {
        this.anio = anio;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getDistrito() {
        return distrito;
    }
    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }
    public String getResolAdmin() {
        return resolAdmin;
    }
    public void setResolAdmin(String resolAdmin) {
        this.resolAdmin = resolAdmin;
    }
    public String getNumExpediente() {
        return numExpediente;
    }
    public void setNumExpediente(String numExpediente) {
        this.numExpediente = numExpediente;
    }
    public String getNumActa() {
        return numActa;
    }
    public void setNumActa(String numActa) {
        this.numActa = numActa;
    }

}
