
public abstract class Personaje {
    protected String nombre;
    protected int vida;
    protected Posicion posicion;

    public Personaje(String nombre, int vida, Posicion posicion) {
        this.nombre = nombre;
        this.vida = vida;
        this.posicion = posicion;
    }

    public abstract void mover(int dx, int dy);

    public Posicion getPosicion() {
        return posicion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }
}