public class Mapa {
    private final int filas;
    private final int columnas;
    private Celda[][] celdas;
    private Snake snake;

    public Mapa(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        celdas = new Celda[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                celdas[i][j] = new Celda();
            }
        }
    }

    public void colocarSnake(Snake snake, int x, int y) {
        this.snake = snake;
        snake.getPosicion().setX(x);
        snake.getPosicion().setY(y);
        celdas[x][y].setSnake(snake);
    }

    public void colocarItem(Item item, int x, int y) {
        celdas[x][y].setItem(item);
    }

    public void colocarGuardia(Guardia guardia, int x, int y) {
        guardia.getPosicion().setX(x);
        guardia.getPosicion().setY(y);
        celdas[x][y].agregarGuardia(guardia);
    }

    public void colocarPuerta(int x, int y) {
        celdas[x][y].setPuerta('p');
    }

    public void mostrarMapa() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(celdas[i][j].getRepresentacion() + " ");
            }
            System.out.println();
        }
    }

    public Celda getCelda(int x, int y) {
        return celdas[x][y];
    }

    public int getFilas() { return filas; }
    public int getColumnas() { return columnas; }
}