package memory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class MemoryManager {

	private int myNumberOfPages;
	private int myPageSize; // In bytes
	private int myNumberOfFrames;
	private int[] myPageTable; // -1 if page is not in physical memory
	private int[] myFrameTable;
	private byte[] myRAM; // physical memory RAM
 	private RandomAccessFile myPageFile;
	private int myNextFreeFramePosition = 0;
	private int myNumberOfpageFaults = 0;
	private int myPageReplacementAlgorithm = 0;

	public MemoryManager(int numberOfPages, int pageSize, int numberOfFrames, String pageFile,
			int pageReplacementAlgorithm) {

		myNumberOfPages = numberOfPages;
		myPageSize = pageSize;
		myNumberOfFrames = numberOfFrames;
		myPageReplacementAlgorithm = pageReplacementAlgorithm;

		initPageTable();
		initFrameTable();
		myRAM = new byte[myNumberOfFrames * myPageSize];

		try {

			myPageFile = new RandomAccessFile(pageFile, "r");

		} catch (FileNotFoundException ex) {
			System.out.println("Can't open page file: " + ex.getMessage());
		}
	}

	private void initPageTable() {
		myPageTable = new int[myNumberOfPages];
		for (int n = 0; n < myNumberOfPages; n++) {
			myPageTable[n] = -1;
		}
	}

		
	public byte readFromMemory(int logicalAddress) {
		int pageNumber = getPageNumber(logicalAddress);
		int offset = getPageOffset(logicalAddress);

		if (myPageTable[pageNumber] == -1) {
			pageFault(pageNumber);
		}

		int frame = myPageTable[pageNumber];
		int physicalAddress = frame * myPageSize + offset;
		byte data = myRAM[physicalAddress];

		System.out.print("Virtual address: " + logicalAddress);
		System.out.print(" Physical address: " + physicalAddress);
		System.out.println(" Value: " + data);
		return data;
	}

	private int getPageNumber(int logicalAddress) {
		// Implement by student in task one
		int pageNumber= logicalAddress / myPageSize;
		return pageNumber;
	}

	private int getPageOffset(int logicalAddress) {
		// Implement by student in task one
		int pageOffset = logicalAddress % myPageSize;
		return pageOffset;
	}

	private void pageFault(int pageNumber) {
		if (myPageReplacementAlgorithm == Seminar3.NO_PAGE_REPLACEMENT)
			handlePageFault(pageNumber);

		if (myPageReplacementAlgorithm == Seminar3.FIFO_PAGE_REPLACEMENT)
			handlePageFaultFIFO(pageNumber);

		if (myPageReplacementAlgorithm == Seminar3.LRU_PAGE_REPLACEMENT)
			handlePageFaultLRU(pageNumber);
	
		readFromPageFileToMemory(pageNumber);
	}

	private void readFromPageFileToMemory(int pageNumber) {
		try {
			int frame = myPageTable[pageNumber];
			
			//if (frame == -1)
			if (frame == -1)
				frame = myNextFreeFramePosition;
			//if (frame != -1)
			
			if (frame < myNumberOfFrames){
				myNextFreeFramePosition++;
				myPageFile.seek(pageNumber * myPageSize);
			for (int b = 0; b < myPageSize; b++)
				myRAM[frame * myPageSize + b] = myPageFile.readByte();
			myPageTable[pageNumber] = frame;
			}else{
				resetMyNextFreeFramePosition();
			}	
			//	frame = handlePageFault(pageNumber);
			
				
		} catch (IOException ex) {

		}
	}

	public int getNumberOfPageFaults() {
		return myNumberOfpageFaults;
	}
	
	public void resetMyNextFreeFramePosition() {
		myNextFreeFramePosition = 0;
	}
	
	private void initFrameTable (){
		myFrameTable = new int [myNumberOfFrames];
		for (int i = 0; i < myNumberOfFrames; i++){
			myFrameTable[i] = 0;
		//System.out.println(myFrameTable);	

}}


	
	private void handlePageFault(int pageNumber) {
		// Implement by student in task one
		// This is the simple case where we assume same size of physical and logical
		// memory
		// nextFreeFramePosition is used to point to next free frame position
		// what we have to do here read from backing file and load into RAM
		// Increment the page fault count
		myNumberOfpageFaults++;		
		/*int frame = -1;
		for (int i =0 ;i< myRAM.length; i++){
			if (myRAM[i]==0){
				frame = i; // Store the index of the first occurrence
				break; // Exit the loop after finding the first 0 
		}
	}
		if (frame !=-1){	
			return frame;	
		}else{
			System.out.println("No Free frame available.");
			return -1;
		}*/}
	
	
		/*frameTable(myNumberOfFrames);
		int frame = myPageTable[pageNumber];
		if (frame == 0)
			readFromMemory(pageNumber);
		else
			System.out.println("Frame occupied.");	
		}	*/
		

			
			
	private void handlePageFaultFIFO(int pageNumber) {
		// Implement by student in task two
		// this solution allows different size of physical and logical memory
		// page replacement using FIFO
		// Note depending on your solution, you might need to change parts of the
		// supplied code, this is allowed.
		myNumberOfpageFaults++;


	}

	private void handlePageFaultLRU(int pageNumber) {
		// Implement by student in task three
		// this solution allows different size of physical and logical memory
		// page replacement using LRU
		// Note depending on your solution, you might need to change parts of the
		// supplied code, this is allowed.
	}
}
