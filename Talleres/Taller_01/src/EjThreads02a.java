// Ejemplo 4: Creación de threads con un valor inicial – como implementación de la interface Runnable

public class EjThreads02a implements Runnable {
    private int n;

    public EjThreads02a(int n) {
        System.out.println("Implementando la interfaz Runnable.");
        this.n = n;
    }

    public void run() {
        System.out.println("El valor incial es: " + n);
    }

    public static void main(String[] args) {
        Thread t = new Thread(new EjThreads02a(5));

        t.start();
    }
}
