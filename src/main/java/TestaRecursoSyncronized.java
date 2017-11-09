
public class TestaRecursoSyncronized extends Thread {

	public static int segundos = 1;

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new TestaRecursoSyncronized().start();
		}
		
		System.err.println("Fim criacao das 10 threads, mas contando relogio.");
	}

	private static synchronized void relogio() {
		System.out.println(segundos++);
		
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		relogio();
		
	}
}
