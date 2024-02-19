import java.util.concurrent.ThreadLocalRandom;

public class Ejercicio2 extends Thread {
    // Vamos a generar los numeros aleatorios en un intervalo amplio
    private final static int INT_MAX = 105345;

    // Dimensiones cuadradas
    private final static int DIM = 3;

    // Matriz
    private static int[][] matriz = new int[DIM][DIM];

    // Mayor global
    private static int mayor = -1;

    // Mayor local
    private int mayorFila = -1;
    
    // idThread counter
    private static int idThreadCounter = -1;

    // ID Thread
    private int idThread;

    // Fila a registrar
    private int fila;

    // Constructor
    public Ejercicio2(int pFila) {
        this.fila = pFila;
    }

    // Generar la matriz con numeros aleatorios
    public static void crearMatriz() {
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                matriz[i][j] = ThreadLocalRandom.current().nextInt(0, INT_MAX);
            }
        }
        // Imprimir la matriz
        System.out.println("Matriz:");
        System.out.println("=====================");
        imprimirMatriz();
    }

    // Imprimir la matriz en consola
    private static void imprimirMatriz() {
        for (int i = 0; i < DIM; i ++) {
            for (int j = 0; j < DIM ; j++) {
                System.out.print(matriz[i][j] + "\t");
            }
            System.out.println();
        }
    }

    // Asignar idDelThread
    private synchronized void assignThreadId() {
        Ejercicio2.idThreadCounter += 1;
        this.idThread = Ejercicio2.idThreadCounter;
    }

    @Override
    public void run() {
        assignThreadId();
        for (int j = 0; j < DIM; j++) {
            if (this.mayorFila < matriz[this.fila][j]) {
                this.mayorFila = matriz[this.fila][j];
            }
        }
        if (this.mayorFila > mayor) {
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            mayor = this.mayorFila;
            String warn = String.format("====== Nuevo Maximo Encontrado ====== \n" +
                "ID Thread: %d - Maximo local actual: %d - Maximo global: %d \n" +
                "\n",
                this.idThread,
                mayor,
                this.mayorFila);
            System.out.println(warn);
        }

        //Resultados
        String msg = String.format("ID Thread: %d - Maximo Local: %d - Maximo Global: %d",
            this.idThread,
            this.mayorFila,
            mayor);
        System.out.println(msg);
    }

    // Main
    public static void main(String [] args) {
        System.out.println("Busqueda concurrente por una matriz");

        // Iniciar la matriz
        Ejercicio2.crearMatriz();
        System.out.println();
        System.out.println("Iniciando la busqueda por matriz\n");

        // Iniciar busqueda
        Ejercicio2[] bThreads = new Ejercicio2[DIM];
        for (int i = 0; i < DIM; i++) {
            bThreads[i] = new Ejercicio2(i);
            bThreads[i].start();
        }
    }
}


