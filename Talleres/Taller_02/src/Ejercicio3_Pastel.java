import java.util.Scanner;

public class Ejercicio3_Pastel {
    private float alto;
    private float radio;
    private String sabor;
    private String color;
    private boolean estado;

    public Ejercicio3_Pastel() {
        this.estado = false;
    }

    public void hacerPedido(float alto, float radio, String sabor, String color) {
        this.alto = alto;
        this.radio = radio;
        this.sabor = sabor;
        this.color = color;
        this.estado = true;
    }

    public synchronized void consolaHacerPedido() {
        Scanner scanner = new Scanner(System.in);

        // Solicitar y leer el alto del pedido
        System.out.println("Introduce el alto del pedido: ");
        float alto = scanner.nextFloat();

        // Solicitar y leer el radio del pedido
        System.out.println("Introduce el radio del pedido: ");
        float radio = scanner.nextFloat();

        // Limpiar el buffer del scanner (debido al cambio de nextFloat a nextLine)
        scanner.nextLine();

        // Solicitar y leer el sabor del pedido
        System.out.println("Introduce el sabor del pedido: ");
        String sabor = scanner.nextLine();

        // Solicitar y leer el color del pedido
        System.out.println("Introduce el color del pedido: ");
        String color = scanner.nextLine();

        // Hacer el pedido con los datos introducidos
        hacerPedido(alto, radio, sabor, color);

        System.out.println("Pedido realizado con éxito!");
    }

    public synchronized void consultarDetallesPedido() {
        String detalles = String.format("====== Nuevo Pedido ====== \n" +
                "Alto: %d - Radio: %d - Sabor: %d - Color: %d \n" +
                "\n",
                this.getAlto(),
                this.getRadio(),
                this.getSabor(),
                this.getColor());
            System.out.println(detalles);
    }

    public boolean consultarEstadoPedido() {
        if (estado != false) {
            return true;
        } 
        return false;
    }

    private float getAlto() {
        return this.alto;
    }

    private void setAlto(float alto) {
        this.alto = alto;
    }

    private float getRadio() {
        return this.radio;
    }

    private void setRadio(float radio) {
        this.radio = radio;
    }

    private String getSabor() {
        return this.sabor;
    }

    private void setSabor(String sabor) {
        this.sabor = sabor;
    }

    private String getColor() {
        return this.color;
    }

    private void setColor(String color) {
        this.color = color;
    }

}
