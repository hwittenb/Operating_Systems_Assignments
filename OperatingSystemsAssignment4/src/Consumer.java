/***************
Course: CS 3502 - Operating Systems
Section: 1
Name: Harrison Wittenbrook
***************/

public class Consumer extends Thread{
	private Semaphore mutex;
	private Semaphore full;
	private Semaphore empty;
	
	private int index;			//tracks the index of the consumer in the buffer 
	
	public Consumer(Semaphore m, Semaphore f, Semaphore e) {
		mutex = m;
		full = f;
		empty = e;
		index = 0;
	}
	
	public void run(){
		while(true) {
			full.swait();		//waits for a filled slot in the buffer
			mutex.swait();		//wait for the producer to not be accessing the buffer

			char c = ProdconSync.buffer[index];		//stores the current element in the buffer to be consumed
			ProdconSync.buffer[index] = '0';		//removes the element from the buffer
			
			if(index == ProdconSync.buffer.length-1) {	//checks where the index of the consumer should be
				index = 0;
			}
			else {
				index ++;
			}
			try {
			if(!Character.isAlphabetic(c)) {
				throw new Exception("accessed empty spot");
			}}
			catch(Exception ex) {
				System.out.println(ex);
				System.exit(0);
			}
			
			printBuffer(c);		//prints the buffer with 0's representing empty spaces
			
			mutex.signal();		//signals that consumer is done accessing the buffer
			empty.signal();		//signals that there is another empty slot in the buffer

			//System.out.println(c);		//prints out the consumed character
		}
	}
	
	private void printBuffer(char c) {
		System.out.println("Consumer removed " +c +" from buffer: ");
		for (int i = 0; i < ProdconSync.buffer.length; i++) {
			System.out.print(ProdconSync.buffer[i] + ", ");
		}
		System.out.println("\n");
	}
}
