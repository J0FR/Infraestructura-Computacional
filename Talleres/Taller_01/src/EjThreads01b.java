// Ejemplo 5: Creación de threads con nombre – como extensión de la clase Thread

public class EjThreads01b extends Thread {
    private String name;

    public EjThreads01b(String name) {
        System.out.println("Extendiendo la clase Thread.");
        this.name = name;
    }

    public void run() {
        System.out.println("El nombre es: " + name);
    }
    
    public static void main(String[] args) {
        EjThreads01b t0 = new EjThreads01b("Thread"+0);
        EjThreads01b t1 = new EjThreads01b("Thread"+1);
        EjThreads01b t2 = new EjThreads01b("Thread"+2);

        t0.start();
        t1.start();
        t2.start();
    }
}
