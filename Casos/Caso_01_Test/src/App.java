import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.CyclicBarrier;
import java.awt.Point;

public class App extends Thread {

    public static Celda[][] matriz;
    public static CyclicBarrier barrera;
    public static int iteracion;

    public static Celda[][] getMatriz() {
        return matriz;
    }

    public static CyclicBarrier getBarrera() {
        return barrera;
    }

    @Override
    public void run() {
        
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Por favor, ingrese el nombre del archivo:");
        String fileName = scanner.nextLine();
        String filePath = "./Datos/" + fileName;
        boolean[][] valores_de_matriz = readFile(filePath);
        System.out.println("Por favor, ingrese el número de iteraciones:");
        while (!scanner.hasNextInt()) {
            System.out.println("El valor ingresado no es un entero válido. Por favor, ingrese el número de iteraciones:");
            scanner.next();
        }
        iteracion = scanner.nextInt(); 
        scanner.close();
        barrera = new CyclicBarrier(valores_de_matriz.length*valores_de_matriz.length, new Runnable() {
            @Override
            public void run() {
                iteracion--;
                System.out.println("Iteracion decrementada: " + iteracion);
                printMatriz(matriz);
                System.out.println();
            }
        });
        crearMatriz(valores_de_matriz);
        printMatriz(matriz);
        
    }

    public static void movimiento() {

    }

    public static boolean[][] readFile(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line = br.readLine();
        int numberOfLines = Integer.parseInt(line);
        boolean[][] values = new boolean[numberOfLines][];

        for (int i = 0; i < numberOfLines; i++) {
            line = br.readLine(); 
            if (line != null) {
                String[] stringValues = line.split(","); 
                boolean[] booleanValues = new boolean[stringValues.length];
                for (int j = 0; j < stringValues.length; j++) {
                    booleanValues[j] = Boolean.parseBoolean(stringValues[j]); 
                }
                values[i] = booleanValues; 
            }
        }
        return values;
    }

    public static void crearMatriz(boolean[][] valores_matriz) {
        int tamanio_matriz = valores_matriz.length;
        matriz = new Celda[tamanio_matriz][tamanio_matriz];
        int id = 1;
        for (int i = 0; i < tamanio_matriz; i++) {
            for (int j = 0; j < tamanio_matriz; j++) {
                Celda celda = new Celda(id, new Point(i, j), valores_matriz[i][j]);
                celda.start();
                matriz[i][j] = celda;
                id++;
            }
        }
    }

    public static void printMatriz(Celda[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                Celda celda = matriz[i][j];
                System.out.print(celda.getEstado() ? "T " : "F ");
            }
            System.out.println();
        }
    }
}
