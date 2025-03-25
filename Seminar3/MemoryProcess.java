package memory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MemoryProcess {

	private String myFilename;
	private MemoryManager myMemoryManager;

	public MemoryProcess(String filename, MemoryManager mmu) {
		myFilename = filename;
		myMemoryManager = mmu;
	}

	public void callMemory() {
		try {
			Scanner reader = new Scanner(new File(myFilename));
			int address;
			int nbrOfIterations = 0;

			while (reader.hasNextInt()) {
				nbrOfIterations++;
				address = reader.nextInt();
				myMemoryManager.readFromMemory(address);
			}
			reader.close();
			System.out.println("Iterations: " + nbrOfIterations);
		} catch (FileNotFoundException ex) {
			System.out.println("Can't open addresses  file: " + ex.getMessage());
		}
	}
}
