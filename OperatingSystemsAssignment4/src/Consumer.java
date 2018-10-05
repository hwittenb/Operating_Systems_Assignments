
public class Consumer extends Thread{
	private Semaphore mutex;
	private Semaphore full;
	private Semaphore empty;
	
	public Consumer(Semaphore m, Semaphore f, Semaphore e) {
		mutex = m;
		full = f;
		empty = e;
	}
	
	public void run() {
		while(true) {
			try {
				full.wait();
				mutex.wait();
			}
			catch(Exception ex) {
				System.exit(0);
			}
			/*************remove an item from a full slot of the buffer***********/
			mutex.signal();
			empty.signal();
			/*************consume data item***********/

		}
	}
}
