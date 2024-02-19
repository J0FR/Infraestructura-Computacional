import java.util.ArrayList;

public class Buffer {
    private ArrayList<Boolean> buff;
    private int n;

    public Buffer(int x) {
        this.n = x;
        buff = new ArrayList<>();
    }

    public synchronized void almacenar(Boolean i) throws InterruptedException {
        while (buff.size() == n)
                wait();
        buff.add(i);
        notify();
    }

    public synchronized Boolean retirar() throws InterruptedException {
        while (buff.size() == 0)
            wait();
        
        Boolean i = (Boolean) buff.remove(0);
        notify();

        return i;
    }
}
