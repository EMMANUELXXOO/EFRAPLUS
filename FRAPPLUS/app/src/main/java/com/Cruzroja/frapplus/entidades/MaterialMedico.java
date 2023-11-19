package com.Cruzroja.frapplus.entidades;

public class MaterialMedico {
    private int id;
    private String ClaveFRAPOrden;

    private String Material;
    private String Cantidad;

    public String getClaveFRAPOrden() {
        return ClaveFRAPOrden;
    }

    public void setClaveFRAPOrden(String claveFRAPOrden) {
        ClaveFRAPOrden = claveFRAPOrden;
    }

    public String getMaterial() {
        return Material;
    }

    public void setMaterial(String material) {
        Material = material;
    }

    public String getCantidad() {
        return Cantidad;
    }

    public void setCantidad(String cantidad) {
        Cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
