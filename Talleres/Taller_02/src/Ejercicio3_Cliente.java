import java.util.concurrent.ThreadLocalRandom;

public class Ejercicio3_Cliente implements Runnable {
    private Ejercicio3_Pastel pastel;
    private Ejercicio3_Pastelero pastelero;

    public Ejercicio3_Cliente(Ejercicio3_Pastel pastel, Ejercicio3_Pastelero pastelero) {
        this.pastel = pastel;
        this.pastelero = pastelero;
    }

    public void run() {
        pastel.consolaHacerPedido();
        pastelero.estadoPastel();
    }
}
