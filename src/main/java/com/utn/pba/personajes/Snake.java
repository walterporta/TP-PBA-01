package com.utn.pba.personajes;

import com.utn.pba.mapa.Posicion;

public class Snake extends Personaje {

   public Snake(String nombre, int vida, Posicion posicion){
        super(nombre, vida, posicion);
   }

    @Override
    public void mover(int dx, int dy) {
        posicion.setX(posicion.getX() + dx);
        posicion.setY(posicion.getY() + dy);
    }
}

