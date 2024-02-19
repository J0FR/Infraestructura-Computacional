import java.util.concurrent.ThreadLocalRandom;

public class Ejercicio1 extends Thread {

    private final static int INT_MAX = 105345;
    private final static int DIM = 3;
    private static int[][] matriz = new int[DIM][DIM];
    private static int mayor = -1;
    private int mayorFila = -1;
    private int fila;
    private int idThread;

    public Ejercicio1(int fila, int pIdThread) {
        this.fila = fila;
        this.idThread = pIdThread;
    }

    public static void crearMatriz() {
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                matriz[i][j] = ThreadLocalRandom.current().nextInt(0, INT_MAX);
            }
        }
        System.out.println("Matriz:");
        System.out.println("=====================");
        imprimirMatriz();
    }

    private static void imprimirMatriz() {
        for (int i = 0; i < DIM; i ++) {
            for (int j = 0; j < DIM ; j++) {
                System.out.print(matriz[i][j] + "\t");
            }
            System.out.println();
        }
    }

    @Override
    public void run() {
        for (int j = 0; j < DIM; j++) {
            if (this.mayorFila < matriz[this.fila][j]) {
                this.mayorFila = matriz[this.fila][j];
            }
        }
        putMaximo();
        //Resultados
        String msg = String.format("ID Thread: %d - Maximo Local: %d - Maximo Global: %d",
            this.idThread,
            this.mayorFila,
            mayor);
        System.out.println(msg);
    }

    public synchronized void putMaximo() {
        if (mayorFila > mayor) {
            Ejercicio1.mayor = this.mayorFila;
        }
    }

    public static void main(String[] args) {
        System.out.println("Busqueda concurrente por una matriz");

        // Iniciar la matriz
        Ejercicio1.crearMatriz();
        System.out.println();
        System.out.println("Iniciando la busqueda por matriz\n");

        // Iniciar busqueda
        Ejercicio1[] bThreads = new Ejercicio1[DIM];
        for (int i = 0; i < DIM; i++) {
            bThreads[i] = new Ejercicio1(i, i);
            bThreads[i].start();
        }

    }
}
