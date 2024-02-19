import java.util.concurrent.ThreadLocalRandom;

public class Ejercicio3_Pastelero implements Runnable {
    private Ejercicio3_Pastel pastel;
    
    public Ejercicio3_Pastelero(Ejercicio3_Pastel pastel) {
        this.pastel = pastel;
    }

    public void run() {
        while (getPastel().consultarEstadoPedido()) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("Hare el pastel!");
        try {
            int val = ThreadLocalRandom.current().nextInt(5000, 15000);
            Thread.sleep(val);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Termine el pastel!");
        terminePastel();
    }

    public synchronized void estadoPastel() {
        try {
            wait();
            System.out.println("Toma tu pastel;");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public synchronized void terminePastel() {
        notify();
    }

    private Ejercicio3_Pastel getPastel() {
        return this.pastel;
    }
}
