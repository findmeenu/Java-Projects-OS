package readerwriter;

import java.util.*;

public class RWLock {
    private final ReentrantReadWriteLock lock;

    // Constructor to initialize the lock with fairness enabled
    public RWLock(){
        // Set fairness to true to ensure no starvation (FIFO policy)
        lock = new ReentrantReadWriteLock(true);   
    }

    public void acquireRead(){
        lock.readLock().lock();

    }

    public void acquireWrite(){
        lock.writeLock().lock();

    }

    public void releaseRead(){
       lock.readlock().unlock();
    }

    public void releaseWrite(){
        lock.writeLock().unlock();
    }

}
