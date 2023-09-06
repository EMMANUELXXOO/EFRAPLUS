package com.Cruzroja.frapplus.funciones;

public class CharacterWithCoordinates {
    private String text;
    private float realX;
    private float realY;

    public CharacterWithCoordinates(String text, float realX, float realY) {
        this.text = text;
        this.realX = realX;
        this.realY = realY;
    }

    public String getText() {
        return text;
    }

    public float getRealX() {
        return realX;
    }

    public float getRealY() {
        return realY;
    }
}

