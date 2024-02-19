public class Ejercicio3 {
    public static void main(String[] args) {
        Ejercicio3_Pastel pastel = new Ejercicio3_Pastel();
        Ejercicio3_Pastelero pastelero = new Ejercicio3_Pastelero(pastel);
        Thread cliente = new Thread(new Ejercicio3_Cliente(pastel, pastelero));
        Thread pastelero_thread = new Thread(pastelero);

        cliente.start();
        pastelero_thread.start();
    }
}
