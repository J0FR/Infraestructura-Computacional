// Ejemplo 8: Creación de threads con nombre usando ciclos – como implementación de la interface Runnable – Uso de arreglos

public class EjThreads02c implements Runnable {
    private final static int MAX = 3;
    private String name;

    public EjThreads02c(String name) {
        System.out.println("Implementando la interfaz Runnable");
        this.name = name;
    }

    public void run() {
        System.out.println("El nombre es: " + name);
    }

    public static void main(String[] args) {
        Thread [] ta = new Thread[MAX];

        for (int i = 0; i < ta.length; i ++) {
            ta[i] = new Thread(new EjThreads02c("Thread" + i));
        }

        for (int i = 0; i < ta.length; i++) {
            ta[i].start();
        }
    }
}
