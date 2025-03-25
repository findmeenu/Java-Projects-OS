package readerwriter;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Reader_ implements Runnable {
	private int myId;
	private Data myData; //data type here is Data

	//constructor
	public Reader_(int id, Data data) {
		myId = id;
		myData = data;
	}

	@Override
	public void run() {
		performRead();
	}

	private void performRead() {
		for (int i = 0; i < 7; i++) {
			try {
				// to make tread sleep between 0 to 100 ms. int here is cast to convert result from double to int. 
				//As Math.random() returns a double value between 0.0 (inclusive) and 1.0 (exclusive)
				//Thread.sleep() requires a parameter of type long (or int)
				Thread.sleep((int) (Math.random() * 100));
				// Calling read function/method
				myData.read(myId);
			} catch (InterruptedException ex) {
				Logger.getLogger(Reader_.class.getName()).log(Level.SEVERE, null, ex);
			
			}
		}
	}
}
