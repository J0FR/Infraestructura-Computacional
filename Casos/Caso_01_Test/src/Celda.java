import java.util.concurrent.BrokenBarrierException;
import java.awt.Point;

public class Celda extends Thread {
    private int id;
    private boolean estado;
    private Point ubicacion;
    private Buffer buff;

    public Celda(int id, Point ubicacion, boolean estado) {
        this.id = id;
        this.estado = estado;
        this.ubicacion = ubicacion;
        this.buff = new Buffer(ubicacion.x + 5);
    }

    public void run() {
        try {
            App.barrera.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

        while (App.iteracion > 0) {
            try {
                notificarEstado();
                realizarMovimiento();
                System.out.println("Celda " + id + " en la iteracion " + App.iteracion + " tiene el estado " + estado);
                App.barrera.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        
        }

    }

    public boolean getEstado() {
        return this.estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Point getUbicacion() {
        return this.ubicacion;
    }

    public Buffer getBuffer() {
        return this.buff;
    }

    public void realizarMovimiento() {
        int numTrue = getBuffer().getNumTrue();
        if (numTrue == 3) {
            setEstado(true);
        } else if (numTrue > 3 || numTrue == 0) {
            setEstado(false);
        } else if (numTrue >= 1 && numTrue <= 3) {
            
        }
        getBuffer().restartNumTrue();
    }

    public void notificarEstado() throws InterruptedException {
        int x = getUbicacion().x;
        int y = getUbicacion().y;
        int len_matriz = App.getMatriz()[0].length;
        if (x - 1 >= 0) {
            Celda celda_vecina = App.getMatriz()[x - 1][y];
            celda_vecina.getBuffer().almacenar(getEstado());
        }
        if (x + 1 < len_matriz) {
            Celda celda_vecina = App.getMatriz()[x + 1][y];
            celda_vecina.getBuffer().almacenar(getEstado());
        }
        if (y - 1 >= 0) {
            Celda celda_vecina = App.getMatriz()[x][y - 1];
            celda_vecina.getBuffer().almacenar(getEstado());
        }
        if (y + 1 < len_matriz) {
            Celda celda_vecina = App.getMatriz()[x][y + 1];
            celda_vecina.getBuffer().almacenar(getEstado());
        }
    }
}
