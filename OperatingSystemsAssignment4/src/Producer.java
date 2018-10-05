import java.util.*;

public class Producer extends Thread{
	private Semaphore mutex;
	private Semaphore full;
	private Semaphore empty;
	
	private Random rand;
	
	public Producer(Semaphore m, Semaphore f, Semaphore e) {
		mutex = m;
		full = f;
		empty = e;
		rand = new Random();
	}
	
	public void run() {
		while(true) {
			/*************Produce an item***********/
			try {
				empty.wait();
				mutex.wait();
			}
			catch(Exception ex) {
				System.exit(0);
			}
			/*************Deposit an item into an empty slot of the buffer***********/
			mutex.signal();
			full.signal();
		}
	}
	
	private char produceCharacter() {
		return (char) (rand.nextInt(26)+97);
	}
}
