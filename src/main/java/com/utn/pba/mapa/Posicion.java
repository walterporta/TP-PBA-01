package com.utn.pba.mapa;

public class Posicion {
    private int x;
    private int y;

    public Posicion(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }

    @Override
    public String toString() {
        return "Posicion{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public boolean equals(Posicion otraPosicion) {
        return this.x == otraPosicion.x && this.y == otraPosicion.y;
    }

}