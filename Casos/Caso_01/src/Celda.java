import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.awt.Point;

public class Celda extends Thread {

    private int id = 0;
    private Buffer buffer;
    private Point ubicacion;
    private ArrayList<Point> vecinos;
    private boolean estado;
    CountDownLatch latch;
    CountDownLatch latchOrden;

    public Celda(int id, Point ubicacion, boolean estado) {
        this.id = id;
        this.buffer = new Buffer(ubicacion.x + 2);
        this.ubicacion = ubicacion;
        this.vecinos = new ArrayList<Point>();
        this.estado = estado;
        this.asignarVecinos();
        this.latch = new CountDownLatch(this.vecinos.size());
        this.latchOrden = new CountDownLatch(this.ubicacion.y * Test.matriz.length);
    }

    @Override
    public void run() {
        CyclicBarrier barrera = Test.getBarrera();

        // Esperar a que todas las celdas estén listas
        try {
            barrera.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

        // Realizar las iteraciones
        while (Test.iteraciones >= 0) {
            System.out.println("Celda: " + this.getCeldaId() + " - Iteraciones: " + Test.iteraciones);
            System.out.println("Celda " + this.id + " tiene numero de vecinos: " + this.getVecinos().size());
            try {

                System.out.println("Celda: Bre" + this.getCeldaId() + " - Estado: " + this.getEstado() + " - Tiene numero de valor: " + (this.ubicacion.y * Test.matriz.length));
                latchOrden.await();
                System.out.println("Celda: pase orden " + this.getCeldaId() + " - Estado: " + this.getEstado() + " - Tiene numero de vecinos: " + this.getVecinos().size());
                this.notificarEstado();
                
                for (int i = 0; i < Test.getMatriz().length; i++) {
                    for (int j = 0; j < Test.getMatriz()[i].length; j++) {
                        Celda celda = Test.getMatriz()[i][j];
                        celda.notificarLatchOrden();
                    }
                    System.out.println();
                }

                latch.await();
                this.actualizarEstado();
                // System.out.println("Celda: " + this.getCeldaId() + " - Estado: " + this.getEstado() + " - Tiene numero de vecinos: " + this.getVecinos().size());
                Test.getBarrera().await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    private void notificarEstado() {
        for (Point vecino_ubicacion : this.getVecinos()) {
            Celda vecino = Test.getMatriz()[vecino_ubicacion.x][vecino_ubicacion.y];
            vecino.getBuffer().almacenar(estado);
            vecino.notificarLatch();
        }
    }

    private void actualizarEstado() {
        int contador = 0;
        for (Point vecino_ubicacion : this.getVecinos()) {
            Celda vecino = Test.getMatriz()[vecino_ubicacion.x][vecino_ubicacion.y];
            if (vecino.getBuffer().retirar()) {
                contador++;
            }
        }
        
        // Nace: Si una celda muerta tiene exactamente 3 celdas vecinas vivas "nace" (es decir, al turno siguiente estará viva).
        if (!this.estado && contador == 3) {
            this.setEstado(true);
        }
        // Muere: una celda viva puede morir por uno de 2 casos: 
        // Sobrepoblación: si tiene más de tres celdas vivas alrededor o 
        // Aislamiento: si no tiene celdas vivas alrededor. 
        else if (this.estado && (contador < 1 || contador > 3)) {
            this.setEstado(false);
        }
        // Vive: una celda se mantiene viva si tiene entre 1 y 3 celdas vecinas vivas a su alrededor. 
        else if (this.estado && (contador >= 1 || contador <= 3)) {
            this.setEstado(true);
        }
    }

    private void asignarVecinos() {
        // Asignar vecino derecha
        if (this.getUbicacion().x + 1 < Test.getMatriz().length) {
            this.vecinos.add(new Point(this.getUbicacion().x + 1, this.getUbicacion().y));
        }
        // Asignar vecino diagonal derecha arriba
        if (this.getUbicacion().x + 1 < Test.getMatriz().length && this.getUbicacion().y + 1 < Test.getMatriz().length) {
            this.vecinos.add(new Point(this.getUbicacion().x + 1, this.getUbicacion().y + 1));
        }
        // Asignar vecino diagonal derecha abajo
        if (this.getUbicacion().x + 1 < Test.getMatriz().length && this.getUbicacion().y - 1 >= 0) {
            this.vecinos.add(new Point(this.getUbicacion().x + 1, this.getUbicacion().y - 1));
        }
        // Asignar vecino abajo
        if (this.getUbicacion().y - 1 >= 0) {
            this.vecinos.add(new Point(this.getUbicacion().x, this.getUbicacion().y - 1));
        }
        // Asignar vecino arriba
        if (this.getUbicacion().y + 1 < Test.getMatriz().length) {
            this.vecinos.add(new Point(this.getUbicacion().x, this.getUbicacion().y + 1));
        }
        // Asignar vecino diagonal izquierda arriba
        if (this.getUbicacion().x - 1 >= 0 && this.getUbicacion().y + 1 < Test.getMatriz().length) {
            this.vecinos.add(new Point(this.getUbicacion().x - 1, this.getUbicacion().y + 1));
        }
        // Asignar vecino izquierda
        if (this.getUbicacion().x - 1 >= 0) {
            this.vecinos.add(new Point(this.getUbicacion().x - 1, this.getUbicacion().y));
        }
        // Asignar vecino diagonal izquierda abajo
        if (this.getUbicacion().x - 1 >= 0 && this.getUbicacion().y - 1 >= 0) {
            this.vecinos.add(new Point(this.getUbicacion().x - 1, this.getUbicacion().y - 1));
        }
    }
    
    public synchronized void notificarLatch() {
        this.latch.countDown();
    }

    public synchronized void notificarLatchOrden() {
        this.latchOrden.countDown();
    }

    public int getCeldaId() {
        return id;
    }

    private ArrayList<Point> getVecinos() {
        return this.vecinos;
    }
    
    private Point getUbicacion() {
        return this.ubicacion;
    }

    public boolean getEstado() {
        return this.estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Buffer getBuffer() {
        return this.buffer;
    }
}
