public class Item {
    private String nombre;
    private char simbolo;

    public Item(String nombre, char simbolo) {
        this.nombre = nombre;
        this.simbolo = simbolo;
    }

    public String getNombre() { return nombre; }
    public char getSimbolo() { return simbolo; }
}
