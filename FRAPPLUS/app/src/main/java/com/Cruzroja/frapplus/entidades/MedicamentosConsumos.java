package com.Cruzroja.frapplus.entidades;

public class MedicamentosConsumos {
    private int id;
    private String ClaveFRAPOrden;

    private String HoraCaptura;
    private String Medicamento;
    private String Dosis;
    private String Via;
    private String TE;

    public String getClaveFRAPOrden() {
        return ClaveFRAPOrden;
    }

    public void setClaveFRAPOrden(String claveFRAPOrden) {
        ClaveFRAPOrden = claveFRAPOrden;
    }

    public String getHoraCaptura() {
        return HoraCaptura;
    }

    public void setHoraCaptura(String horaCaptura) {
        HoraCaptura = horaCaptura;
    }

    public String getMedicamento() {
        return Medicamento;
    }

    public void setMedicamento(String medicamento) {
        Medicamento = medicamento;
    }

    public String getDosis() {
        return Dosis;
    }

    public void setDosis(String dosis) {
        Dosis = dosis;
    }

    public String getVia() {
        return Via;
    }

    public void setVia(String via) {
        Via = via;
    }

    public String getTE() {
        return TE;
    }

    public void setTE(String TE) {
        this.TE = TE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
