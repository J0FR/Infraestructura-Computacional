// Ejemplo 7: Creación de threads con nombre usando ciclos – como extensión de la clase Thread – Uso con arreglos

public class EjThreads01c extends Thread {
    private final static int MAX = 3;

    private String name;

    public EjThreads01c(String name) {
        System.out.println("Extendiendo la clase Thread.");
        this.name = name;
    }

    public void run() {
        System.out.println("El nombre es: " + name);
    }

    public static void main(String[] args) {
        EjThreads01c [] ta = new EjThreads01c[MAX];

        for (int i = 0; i < ta.length; i++) {
            ta[i] = new EjThreads01c("Thead" + i);
        }

        for (int i = 0; i < ta.length; i++) {
            ta[i].start();
        }
    }
}
