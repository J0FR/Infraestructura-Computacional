// Ejemplo 2: Creación de threads como implementación de la interfaz Runnable

public class EjThreads02 implements Runnable {
    public void run() {
        System.out.println("Extendiendo la clase Thread.");
    }

    public static void main(String[] args) {
        Thread t = new Thread(new EjThreads02());

        t.start();
    }
}
