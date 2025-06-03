package com.utn.pba.misiones;

import com.utn.pba.mapa.Mapa;
import com.utn.pba.mapa.Posicion;
import com.utn.pba.personajes.Snake;

public abstract class Mision {
    protected boolean completada;
    protected String descripcion;
    protected Mapa mapa;
    protected Snake snake;

    public Mision() {
        this.completada = false;
        this.snake = new Snake(new Posicion(0, 0));
    }

    // Método para iniciar la misión
    public abstract void iniciarMision();

    // Método para verificar si se cumplió el objetivo
    public abstract boolean verificarObjetivo();

    // Método para mostrar el estado actual de la misión
    public abstract void mostrarEstado();

    // Método para procesar el movimiento del jugador
    public abstract void procesarMovimiento(char direccion);

    // Getters
    public boolean estaCompletada() {
        return completada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Mapa getMapa() {
        return mapa;
    }

    public Snake getSnake() {
        return snake;
    }

    // Método para marcar la misión como completada
    protected void completarMision() {
        this.completada = true;
    }
} 