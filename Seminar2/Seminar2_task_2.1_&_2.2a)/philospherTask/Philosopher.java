package philosophers;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Philosopher implements Runnable{
    private int myId;
    private Table myTable;
    //NEW ADD
    private int numberOfPhilosophers;  // Total number of philosophers
    // END ADD

    public Philosopher(int id,Table table, int noOfPhilosophers){
        myId = id;
        myTable = table;
        numberOfPhilosophers = noOfPhilosophers;
    }

    @Override
    public void run(){
        for(int i =0 ; i < 10; i++){
            try {
              // Simulate thinking
                System.out.println("Philosopher " + myId + " thinks. Iteration "+ i);
                Thread.sleep((int)(Math.random()*10));
                
                
                // Deadlock-free approach: Last philosopher picks up right chopstick first
                // NEW ADD - code in if block
                if (myId == numberOfPhilosophers - 1) {
                    // Pick up right chopstick first
                    myTable.getRightChopstick(myId);
                    System.out.println("Philosopher " + myId + " picks up right chopstick");
                    Thread.sleep((int) (Math.random() * 10));

                    // Pick up left chopstick second
                    myTable.getLeftChopstick(myId);
                    System.out.println("Philosopher " + myId + " picks up left chopstick");

                    System.out.println("Philosopher " + myId + " eats. Iteration "+ i);
                    Thread.sleep((int)(Math.random()*10));
                
                    myTable.releaseRightChopstick(myId);
                    System.out.println("Philosopher " + myId + " drop right");
                    Thread.sleep((int)(Math.random()*10));
                    
                    myTable.releaseLeftChopstick(myId);
                    System.out.println("Philosopher " + myId + " drop left");
                    Thread.sleep((int)(Math.random()*30));
                                
                    
                } else{
                     // All other philosophers pick up left chopstick first
                    myTable.getLeftChopstick(myId);
                    System.out.println("Philosopher " + myId + " pick up left");
                    Thread.sleep((int)(Math.random()*30));
                
                    myTable.getRightChopstick(myId);
                    System.out.println("Philosopher " + myId + " pick up right");
                
                    System.out.println("Philosopher " + myId + " eats. Iteration "+ i);
                    Thread.sleep((int)(Math.random()*10));
                
                    myTable.releaseLeftChopstick(myId);
                    System.out.println("Philosopher " + myId + " drop left");
                    Thread.sleep((int)(Math.random()*30));
                                
                    myTable.releaseRightChopstick(myId);
                    System.out.println("Philosopher " + myId + " drop right");
                    Thread.sleep((int)(Math.random()*10));
                }   
            } catch (InterruptedException ex) {
                Logger.getLogger(Philosopher.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
