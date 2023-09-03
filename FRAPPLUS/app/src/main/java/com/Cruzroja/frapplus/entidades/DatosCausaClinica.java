package com.Cruzroja.frapplus.entidades;

public class DatosCausaClinica {

    private String ClaveFRAPOrden;
    private String OrigenProbable;
    private String primeravez;
    private String SubSecuente;

    public String getClaveFRAPOrden() {
        return ClaveFRAPOrden;
    }

    public void setClaveFRAPOrden(String claveFRAPOrden) {
        ClaveFRAPOrden = claveFRAPOrden;
    }

    public String getOrigenProbable() {
        return OrigenProbable;
    }

    public void setOrigenProbable(String origenProbable) {
        OrigenProbable = origenProbable;
    }

    public String getPrimeravez() {
        return primeravez;
    }

    public void setPrimeravez(String primeravez) {
        this.primeravez = primeravez;
    }

    public String getSubSecuente() {
        return SubSecuente;
    }

    public void setSubSecuente(String subSecuente) {
        SubSecuente = subSecuente;
    }
}
