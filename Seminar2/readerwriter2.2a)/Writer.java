package readerwriter;


import java.util.logging.Level;
import java.util.logging.Logger;


public class Writer implements Runnable{
    private int myId;
    private Data myData;

    public Writer(int id,Data data){
        myId = id;
        myData = data;
    }

    @Override
    public void run(){
    	performWrite(); 
    }
    
    private void performWrite()
    {
    	 for(int i=0 ; i <7; i++){
             try {
                 Thread.sleep((int)(Math.random()*100));
                 myData.write(myId, i);
             } catch (InterruptedException ex) {
                 Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
    }
}
