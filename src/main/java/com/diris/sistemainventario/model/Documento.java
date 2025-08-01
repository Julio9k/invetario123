package com.diris.sistemainventario.model;

import java.sql.Date;

public class Documento {

    private Integer idDocumento; 
    private String nombreDocumento;
    private String numActa;
    private String tipo;
    private String distrito;
    private String direccion;
    private String tomo;
    private String caja;
    private String estado;
    private Date fechaRegistro;
    private Integer anio;
    private String numberExpediente;
    private String resolucionAdmin;

    public Documento() {
    }

    public Integer getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Integer idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getNombreDocumento() {
        return nombreDocumento;
    }

    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

    public String getNumActa() {
        return numActa;
    }

    public void setNumActa(String numActa) {
        this.numActa = numActa;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTomo() {
        return tomo;
    }

    public void setTomo(String tomo) {
        this.tomo = tomo;
    }

    public String getCaja() {
        return caja;
    }

    public void setCaja(String caja) {
        this.caja = caja;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public String getNumberExpediente() {
        return numberExpediente;
    }

    public void setNumberExpediente(String numberExpediente) {
        this.numberExpediente = numberExpediente;
    }

    public String getResolucionAdmin() {
        return resolucionAdmin;
    }

    public void setResolucionAdmin(String resolucionAdmin) {
        this.resolucionAdmin = resolucionAdmin;
    }

    
}
