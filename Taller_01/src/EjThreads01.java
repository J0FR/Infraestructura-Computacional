// Ejemplo 1: Creación de threads como extensión de la clase Thread

public class EjThreads01 extends Thread {
    public void ru() {
        System.out.println("Extendiendo la clase Thread.");
    }

    public static void main(String[] args) {
        EjThreads01 t = new EjThreads01();

        t.start();
    }
}
