package philosophers;

import java.util.concurrent.Semaphore;

public class Table {

    private int nbrOfChopsticks;
	//create an array of Semaphore objects, one for each chopstick
    private Semaphore[] chopstick; // Semaphores to manage chopsticks

    public Table(int nbrOfSticks) {
        nbrOfChopsticks = nbrOfSticks;
        chopstick = new Semaphore[nbrOfChopsticks];
        for (int i = 0; i < nbrOfChopsticks; i++) {
            chopstick[i] = new Semaphore(1); // Each chopstick is a semaphore with 1 permit (available)
        }
    }

    // Method to acquire left chopstick (with semaphore)
    public void getLeftChopstick(int n) {
        try {
            chopstick[n].acquire(); // Acquire the left chopstick
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Method to acquire right chopstick (with semaphore)
    public void getRightChopstick(int n) {
        int pos = (n + 1) % nbrOfChopsticks;
        try {
            chopstick[pos].acquire(); // Acquire the right chopstick
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Method to release left chopstick (with semaphore)
    public void releaseLeftChopstick(int n) {
        chopstick[n].release(); // Release the left chopstick
    }

    // Method to release right chopstick (with semaphore)
    public void releaseRightChopstick(int n) {
        int pos = (n + 1) % nbrOfChopsticks;
        chopstick[pos].release(); // Release the right chopstick
    }
}



/* package philosophers;
import java.uitl.*;

public class Table {

	private int nbrOfChopsticks;
	private boolean chopstick[]; // true if chopstick[i] is available

	public Table(int nbrOfSticks) {
		nbrOfChopsticks = nbrOfSticks;
		// is  used to create an array of boolean values, where each element in the array represents the availability of a chopstick.
		// an empty boolean array contains False by default. so, in this method
		//we have set the arry to True for all chopsticks 
		chopstick = new boolean[nbrOfChopsticks];
		for (int i = 0; i < nbrOfChopsticks; i++) {
			chopstick[i] = true;
		}
	}
	//Marks the left chopstick of philosopher n as unavailable (false).
	public void getLeftChopstick(int n) {
		chopstick[n] = false;
	}

	// Marks the right chopstick of philosopher n as unavailable (false). 
	//Marks the right chopstick of philosopher n as unavailable (false).
	// If the right chopstick is the last one in the array, it wraps around to the first chopstick (circular arrangement).
	public void getRightChopstick(int n) {
		int pos = n + 1;
		if (pos == nbrOfChopsticks)
			pos = 0;
		chopstick[pos] = false;
	}

	public void releaseLeftChopstick(int n) {
		chopstick[n] = true;
	}

	public void releaseRightChopstick(int n) {
		int pos = n + 1;
		if (pos == nbrOfChopsticks)
			pos = 0;
		chopstick[pos] = true;
	}
} */