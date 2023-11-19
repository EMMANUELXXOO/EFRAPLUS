package com.Cruzroja.frapplus.entidades;

public class FrapCancelado {
    private String ClaveFRACOrden;
    private String StatusC;
    private String DelegacionC;

    private String AsignacionC;

    private String MotivodeCancelacion;


    public String getClaveFRACOrden() {
        return ClaveFRACOrden;
    }

    public void setClaveFRACOrden(String claveFRACOrden) {
        ClaveFRACOrden = claveFRACOrden;
    }

    public String getStatusC() {
        return StatusC;
    }

    public void setStatusC(String statusC) {
        StatusC = statusC;
    }

    public String getAsignacionC() {
        return AsignacionC;
    }

    public void setAsignacionC(String asignacionC) {
        AsignacionC = asignacionC;
    }

    public String getDelegacionC() {
        return DelegacionC;
    }

    public void setDelegacionC(String delegacionC) {
        DelegacionC = delegacionC;
    }

    public String getMotivodeCancelacion() {
        return MotivodeCancelacion;
    }

    public void setMotivodeCancelacion(String motivodeCancelacion) {
        MotivodeCancelacion = motivodeCancelacion;
    }
}
