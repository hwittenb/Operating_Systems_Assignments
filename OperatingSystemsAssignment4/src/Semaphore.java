
public class Semaphore {
	private int sem;
	
	public synchronized void swait() {
		while (sem <= 0) {
			try {
				wait();
			}
			catch(Exception e) {
				System.exit(0);
			}
		}
	}
	
	public synchronized void signal() {
		sem++;
		notify();
	}
	
	public Semaphore(int intval) {
		sem = intval;
	}
}
