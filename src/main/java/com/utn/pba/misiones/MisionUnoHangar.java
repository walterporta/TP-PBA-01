package com.utn.pba.misiones;
import com.utn.pba.mapa.Mapa;
import com.utn.pba.personajes.Snake;
import com.utn.pba.mapa.Posicion;
import com.utn.pba.personajes.Guardia;
import com.utn.pba.items.Item;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class MisionUnoHangar extends Mision {

    private static final int TAMANO_MAPA = 7;
    private static final int NUM_GUARDIAS = 3;
    private Mapa mapa;
    private Posicion posicionPuerta;
    private boolean tarjetaRecogida;
    private List<Guardia> guardias;
    private Random random;




    public MisionUnoHangar( Posicion posicionPuerta) {
        super("Mision Uno Hangar");
        this.posicionPuerta = posicionPuerta;
        this.tarjetaRecogida = false;
        this.guardias = new ArrayList<>();
        this.random = new Random();
        this.mapa = new Mapa(TAMANO_MAPA, TAMANO_MAPA);
        inicializarMision();
    }

    private void inicializarMision() {
        Item tarjetaAcceso = new Item("Tarjeta", "L", new Posicion(3, 3));
        mapa.colocarItem(tarjetaAcceso, new Posicion(3, 3));
        
        Item puertaHangar = new Item("Puerta", "H", posicionPuerta);
        mapa.colocarItem(puertaHangar, posicionPuerta);
    
}


public void colocarGuardiasAleatoriamente(Posicion posicionInicialSnake) {
    int guardiasColocados = 0;

    while (guardiasColocados < NUM_GUARDIAS) {
        int x = random.nextInt(TAMANO_MAPA);
        int y = random.nextInt(TAMANO_MAPA);
        Posicion posAleatoria = new Posicion(x, y);

        if (mapa.esPosicionValida(posAleatoria) && mapa.getCelda(posAleatoria).getContenido() == null) {
            if (calcularDistanciaManhattan(posAleatoria, posicionInicialSnake) > 2) {
                 Guardia guardia = new Guardia(posAleatoria);
                 mapa.colocarPersonaje(guardia, posAleatoria);
                 guardias.add(guardia);
                 guardiasColocados++;
            }
        }
    }
}

public int calcularDistanciaManhattan(Posicion pos1, Posicion pos2) {
    return Math.abs(pos1.getX() - pos2.getX()) + Math.abs(pos1.getY() - pos2.getY());
}

@Override
public boolean verificarObjetivo(Snake snake) {
    Posicion posicionSnake = snake.getPosicion();
    
    // Añadir impresiones de depuración
    System.out.println("DEBUG: Verificar objetivo - Posición Snake: " + posicionSnake);
    System.out.println("DEBUG: Verificar objetivo - Posición Puerta: " + posicionPuerta);
    System.out.println("DEBUG: Verificar objetivo - Tarjeta recogida: " + tarjetaRecogida);

    // Primero verificamos si Snake está en la posición de la puerta y tiene la tarjeta
    if (posicionSnake.equals(posicionPuerta) && tarjetaRecogida) {
        setCompletada(true);
        return true;
    }
    
    // Si Snake está en la posición de la tarjeta (3,3) y no la ha recogido aún
    if (!tarjetaRecogida && posicionSnake.equals(new Posicion(3, 3))) {
        tarjetaRecogida = true;
        System.out.println("¡Has recogido la tarjeta de acceso!");
        return false;
    }
    
    return false;
}


public Mapa getMapa() {
    return mapa;
}

public void moverGuardias() {
    for (Guardia guardia : guardias) {
        Posicion posActual = guardia.getPosicion();
        List<Posicion> movimientosPosibles = new ArrayList<>();
        
        // Añadir movimientos alejándose de Snake si está cerca
        Posicion posSnake = null;
        for (int x = 0; x < TAMANO_MAPA; x++) {
            for (int y = 0; y < TAMANO_MAPA; y++) {
                Object contenido = mapa.getCelda(new Posicion(x, y)).getContenido();
                if (contenido instanceof Snake) {
                    posSnake = new Posicion(x, y);
                    break;
                }
            }
        }
        
        if (posSnake != null) {
            int distanciaActual = calcularDistanciaManhattan(posActual, posSnake);
            if (distanciaActual <= 2) {
                // Si está cerca de Snake, priorizar movimientos que lo alejen
                if (posActual.getX() < posSnake.getX()) {
                    movimientosPosibles.add(new Posicion(posActual.getX() - 1, posActual.getY()));
                } else if (posActual.getX() > posSnake.getX()) {
                    movimientosPosibles.add(new Posicion(posActual.getX() + 1, posActual.getY()));
                }
                if (posActual.getY() < posSnake.getY()) {
                    movimientosPosibles.add(new Posicion(posActual.getX(), posActual.getY() - 1));
                } else if (posActual.getY() > posSnake.getY()) {
                    movimientosPosibles.add(new Posicion(posActual.getX(), posActual.getY() + 1));
                }
            }
        }
        
        // Si no hay movimientos de alejamiento o no está cerca de Snake, moverse aleatoriamente
        if (movimientosPosibles.isEmpty()) {
            movimientosPosibles.add(new Posicion(posActual.getX(), posActual.getY() - 1)); 
            movimientosPosibles.add(new Posicion(posActual.getX(), posActual.getY() + 1)); 
            movimientosPosibles.add(new Posicion(posActual.getX() - 1, posActual.getY())); 
            movimientosPosibles.add(new Posicion(posActual.getX() + 1, posActual.getY())); 
        }

        Collections.shuffle(movimientosPosibles);

        for (Posicion nuevaPos : movimientosPosibles) {
             if (mapa.esPosicionValida(nuevaPos)) {
                 Object contenidoDestino = mapa.getCelda(nuevaPos).getContenido();
                 if (contenidoDestino == null) { 
                    mapa.getCelda(posActual).setContenido(null); 
                    mapa.getCelda(nuevaPos).setContenido(guardia); 
                    guardia.setPosicion(nuevaPos); 
                    break; 
                 }
             }
        }
    }
}

public boolean verificarCaptura(Snake snake) {
    Posicion posicionSnake = snake.getPosicion();
    for (Guardia guardia : guardias) {
        if (calcularDistanciaManhattan(posicionSnake, guardia.getPosicion()) <= 1) {
            return true; 
        }
    }
    return false;
}


}