package com.Cruzroja.frapplus.entidades;

public class DatosSERVIDORES {

    private int id;
    private String URLSERVIDOR;
    private String USUARIO;
    private String PASSWORD;



    public String getURLSERVIDOR() {
        return URLSERVIDOR;
    }

    public void setURLSERVIDOR(String URLSERVIDOR) {
        this.URLSERVIDOR = URLSERVIDOR;
    }

    public String getUSUARIO() {
        return USUARIO;
    }

    public void setUSUARIO(String USUARIO) {
        this.USUARIO = USUARIO;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
