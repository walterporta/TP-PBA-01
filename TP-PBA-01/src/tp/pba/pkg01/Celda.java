public class Celda {
    private Snake snake;
    private Guardia[] guardias = new Guardia[05]; // Tamaño fijo
    private int cantidadGuardias = 0; // Contador para saber cuántos hay
    private Item item;

    //Puerta
    private boolean esPuerta = false;
    private char simboloPuerta = ' ';

    // Getters y Setters
    public void setSnake(Snake s) {
        this.snake = s;
    }

    public Snake getSnake() {
        return snake;
    }

    public void agregarGuardia(Guardia g) {
        if (cantidadGuardias < guardias.length) {
            guardias[cantidadGuardias] = g;
            cantidadGuardias++;
        } else {
            System.out.println("No se puede agregar más guardias a esta celda.");
        }
    }

    public Guardia[] getGuardias() {
        // Devuelve solo los guardias agregados, no el array completo
        Guardia[] actuales = new Guardia[cantidadGuardias];
        for (int i = 0; i < cantidadGuardias; i++) {
            actuales[i] = guardias[i];
        }
        return actuales;
    }

    public void setItem(Item i) {
        this.item = i;
    }

    public Item getItem() {
        return item;
    }

    //Puerta
    public void setPuerta(char simbolo) {
        this.esPuerta = true;
        this.simboloPuerta = simbolo;
    }

    public boolean EsPuerta() {
        return esPuerta;
    }

    public boolean tieneEnemigosCerca() {
        return cantidadGuardias > 0;
    }

    // Representación para mostrar la celda
    public char getRepresentacion() {
        if (snake != null) return 'S';
        if (cantidadGuardias > 0) return '*';
        if (item != null) return item.getSimbolo();
        if (esPuerta) return simboloPuerta;
        return '.';
    }
}