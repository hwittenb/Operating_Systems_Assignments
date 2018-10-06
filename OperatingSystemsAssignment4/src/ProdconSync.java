/***************
Course: CS 3502 - Operating Systems
Section: 1
Name: Harrison Wittenbrook
***************/

public class ProdconSync {
	static final int N = 100;
	public static char[] buffer;
	
	public static void main(String[] args) {
		Semaphore mutex = new Semaphore(1);
		Semaphore full = new Semaphore(0);
		Semaphore empty = new Semaphore(N);
		
		Producer prod = new Producer(mutex, full, empty);
		Consumer cons = new Consumer(mutex, full, empty);
		buffer = new char[N];
		
		prod.start();
		cons.start();
	}

}
