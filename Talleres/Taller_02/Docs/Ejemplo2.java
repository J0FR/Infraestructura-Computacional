package L2;

public class Ejemplo2 extends Thread{
	
	private boolean agrega;
	private int id;
	
	private static int recurso = 0;
	
	private static Object o1= new Object();
	
	public Ejemplo2(boolean pAgrega, int pId) {
		this.agrega = pAgrega;
		this.id = pId;
	}
	
	@Override
	public void run() {
		if(agrega) {
			Ejemplo2.recurso += 1;
			System.out.println(this.id + ": Agrega recurso");
		}
		else {
			System.out.println(this.id + ": Intenta acceder al recurso");
			while(recurso == 0) {
			}
			System.out.println(this.id + ": Accede al recurso");
		}
		System.out.println(this.id + ": Termina");
	}
	
	public static void main(String[] args) throws InterruptedException {
		Ejemplo2 e1 = new Ejemplo2(false, 1);
		Ejemplo2 e2 = new Ejemplo2(true, 2);
		e1.start();
		e2.start();
	}
}
