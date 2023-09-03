package com.Cruzroja.frapplus.entidades;

public class DatosPaciente {
    private String ClaveFRAPOrden;
    private String NombreAfiliacion;
    private String Sexo;
    private String Edad;
    private String Domicilio;
    private String Colonia;
    private String Municipio;
    private String Telefono;
    private String Ocupacion;
    private String DerechoAmbiente;

    public String getClaveFRAPOrden() {
        return ClaveFRAPOrden;
    }

    public void setClaveFRAPOrden(String claveFRAPOrden) {
        ClaveFRAPOrden = claveFRAPOrden;
    }

    public String getNombreAfiliacion() {
        return NombreAfiliacion;
    }

    public void setNombreAfiliacion(String nombreAfiliacion) {
        NombreAfiliacion = nombreAfiliacion;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String sexo) {
        Sexo = sexo;
    }

    public String getEdad() {
        return Edad;
    }

    public void setEdad(String edad) {
        Edad = edad;
    }

    public String getDomicilio() {
        return Domicilio;
    }

    public void setDomicilio(String domicilio) {
        Domicilio = domicilio;
    }

    public String getColonia() {
        return Colonia;
    }

    public void setColonia(String colonia) {
        Colonia = colonia;
    }

    public String getMunicipio() {
        return Municipio;
    }

    public void setMunicipio(String municipio) {
        Municipio = municipio;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getOcupacion() {
        return Ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        Ocupacion = ocupacion;
    }

    public String getDerechoAmbiente() {
        return DerechoAmbiente;
    }

    public void setDerechoAmbiente(String derechoAmbiente) {
        DerechoAmbiente = derechoAmbiente;
    }
}
