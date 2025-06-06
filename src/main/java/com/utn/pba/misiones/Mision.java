package com.utn.pba.misiones;

import com.utn.pba.personajes.Snake;

public abstract class Mision {
    protected String nombre;
    protected boolean completada;

    public Mision(String nombre) {
        this.nombre = nombre;
        this.completada = false;
    }
    
    public abstract boolean verificarObjetivo(Snake snake);
    

    public String getNombre() {
        return nombre;
    }

    public boolean estaCompletada() {
        return completada;
    }


    protected void completarMision() {
        this.completada = true;
    }

}
