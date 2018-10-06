/***************
Course: CS 3502 - Operating Systems
Section: 1
Name: Harrison Wittenbrook
***************/

import java.util.*;

public class Producer extends Thread{
	private Semaphore mutex;
	private Semaphore full;
	private Semaphore empty;
	
	private int index;			//tracks the index of the producer in the buffer
	private Random rand;		//random number generator for producing characters
	
	public Producer(Semaphore m, Semaphore f, Semaphore e) {
		mutex = m;
		full = f;
		empty = e;
		
		index = 0;
		rand = new Random();
	}
	
	public void run() {
		while(true) {
			char c = produceCharacter();	//produce an item
			
			empty.swait();					//wait for there to be an empty slot in the buffer
			mutex.swait();					//wait for the consumer to not be accessing the buffer

			ProdconSync.buffer[index] = c;				//deposit produced character into buffer
			if(index == ProdconSync.buffer.length-1) {	//checks where the index index of the producer should be
				index = 0;
			}
			else {
				index++;
			}
			printBuffer(c);			//prints out the current buffer with 0 representing empty spaces
									//also shows character placed into the buffer
			
			mutex.signal();			//signals that producer is done using the mutex
			full.signal();			//signals that another spot in the buffer has been used
		}
	}
	
	private char produceCharacter() {
		//produces a random lowercase letter from a-z
		return (char) (rand.nextInt(26)+97);
	}
	
	private void printBuffer(char c) {
		System.out.println("Producer inserted " +c +" into buffer: ");
		for (int i = 0; i < ProdconSync.buffer.length; i++) {
			System.out.print(ProdconSync.buffer[i] + ", ");
		}
		System.out.println("\n");
	}
}
