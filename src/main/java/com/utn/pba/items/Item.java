package com.utn.pba.items;
import com.utn.pba.mapa.Posicion;

public class Item {
    private String nombre;
    private char simbolo;
    private Posicion posicion;

    public Item(String nombre, char simbolo, Posicion posicion) {
        this.nombre = nombre;
        this.simbolo = simbolo;
        this.posicion = posicion;
    }

    public String getNombre() { return nombre; }
    public char getSimbolo() { return simbolo; }
    public Posicion getPosicion() { return posicion; }
    public void setPosicion(Posicion posicion) { this.posicion = posicion; }

    @Override
    public String toString() {
        return "" + simbolo;
    }   

}