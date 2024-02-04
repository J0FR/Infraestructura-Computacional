package L2;

public class Ejemplo extends Thread{
	
	private boolean agrega;
	private int id;
	
	private static int recurso = 0;
	
	private static Object o1= new Object();
	
	public Ejemplo(boolean pAgrega, int pId) {
		this.agrega = pAgrega;
		this.id = pId;
	}
	
	@Override
	public void run() {
		if(agrega) {
			Ejemplo.recurso += 1;
			System.out.println(this.id + ": Despierta a otro");
			synchronized (o1) {
				o1.notify();
			}
		}
		else {
			if(recurso == 0) {
				try {
					System.out.println(this.id + ": Se duerme");
					synchronized (o1) {
						o1.wait();
					}
					System.out.println(this.id + ": Se despierta");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println(this.id + ": Termina");
	}
	
	public static void main(String[] args) {
		Ejemplo e1 = new Ejemplo(false, 1);
		Ejemplo e2 = new Ejemplo(true, 2);
		e1.start();
		e2.start();
	}
}
