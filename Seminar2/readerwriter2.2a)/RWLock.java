package readerwriter;

public class RWLock {
    // Variables to track the no. of active readers.
    private int activeReader = 0;
    // Variable to know whether a writer is active or not
    private boolean activeWriter = false;
    
    //Task 2 - Starvation
    private int waitingWriter = 0;       // Count of writers waiting to acquire the lock
    // Task 2 end 
    
    // Object to use as a monitor lock
    private final Object lock = new Object();


    public RWLock(){

    }

    public void acquireRead(){
        //synchronized block: The block of code within the synchronized block is known as the critical section. 
        //While a thread holds the lock, no other thread can enter a synchronized block with the same lock object.
        synchronized (lock){
            // Wait while a writer is active
            // And, if waiting writer are greater than 0-----------Cond. for Task 2)
            while (activeWriter || waitingWriter >0){
                try{
                    lock.wait();
                } catch (InterruptedException e){
                    Thread.currentThread().interrupt(); // Restore the interrupted status
                }
            }
            // Safe to proceed as a reader
            activeReader++;
        }

    }

    public void acquireWrite(){
        synchronized (lock) {
            waitingWriter++; // after entering the lock, it will increase the waiting writer count.---Task2
            // Wait if there are active readers or another writer is writing
            while (activeReader > 0 || activeWriter) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Restore the interrupted status
                }

                }
            waitingWriter--; // No longer waiting once it's the writer's turn --------Task 2
                // ok to proceed as a writer
            activeWriter = true ;    
            }
        }
    
    // To release the lock for reader.
    public void releaseRead(){
        //when exiting the lock or reducing reader count, synchronization is crucial to:
        //Prevent race conditions while modifying shared variables like activeReader.
        //Ensure only one thread accesses the critical section at a time.
        synchronized (lock) {
            // Decrease the active readers count
            activeReader--;
            // If no more readers, notify all waiting threads (could include writers)
            if (activeReader == 0) {
                lock.notifyAll();
            }
        }
    }

    public void releaseWrite(){
        synchronized (lock) {
            // Writer is done, release the write lock
            activeWriter = false;
            // Notify all waiting threads (both readers and writers)
            lock.notifyAll();
        }
    }

}
