package readerwriter;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Reader_ implements Runnable {
	private int myId;
	private Data myData;

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
				Thread.sleep((int) (Math.random() * 100));
				myData.read(myId);
			} catch (InterruptedException ex) {
				Logger.getLogger(Reader_.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
}
