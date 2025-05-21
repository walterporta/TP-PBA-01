
public class Snake extends Personaje {

    public Snake(Posicion posicion) {
        super("Snake", 100, posicion);
    }

    @Override
    public void mover(int dx, int dy) {
        posicion.setX(posicion.getX() + dx);
        posicion.setY(posicion.getY() + dy);
    }
}

