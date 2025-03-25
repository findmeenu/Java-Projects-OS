//A Semaphore is a synchronization primitive that restricts the NO. of threads that can access a shared resource at the same time. 
//In our case, each chopstick is a shared resource, and only one philosopher (thread) can use a chopstick at a time.

package philosophers;

public class DiningPhilosophers {

    private static final int NUMBER_OF_PHILOSOPHERS = 5;

	public static void main(String[] args) {
        setupTableAndPhilosophers(NUMBER_OF_PHILOSOPHERS); 
    }
     
   private static void setupTableAndPhilosophers(int numberOfPhilosophers)
    {
   	   Table table = new Table(numberOfPhilosophers);
           for (int i = 0; i < numberOfPhilosophers; i++) {
               Thread thread = new Thread(new Philosopher(i, table, numberOfPhilosophers));
               thread.start();
           }
    	
    }
}
