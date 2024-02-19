import java.util.ArrayList;

public class Buffer {
    private ArrayList buff;
    private int n;

    public Buffer(int n) {
        this.n = n;
        this.buff = new ArrayList(n);
    }

    public synchronized void almacenar(boolean value) {
        while (buff.size() == n) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        buff.add(value);
        notify();
    }

    public synchronized Boolean retirar() {
        while (buff.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        notify();
        return (Boolean) buff.remove(0);
    }
}
