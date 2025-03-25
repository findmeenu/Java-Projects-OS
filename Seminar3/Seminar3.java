package memory;

public class Seminar3 {

	private static final String BACKING_STORE_FILE = "C:\\Users\\MEGU0007\\Desktop\\Operating Systems\\Seminar 3\\Seminar_3\\resources//BACKING_STORE.bin";

	private static final int PAGE_SIZE = 256;
	private static final int NUMBER_OF_PAGES = 256;
	private static final int NUMBER_OF_FRAMES_256 = 256;
	
	private static final int NUMBER_OF_FRAMES_128 = 128;
	private static final int NUMBER_OF_FRAMES_64 = 64;
	private static final int NUMBER_OF_FRAMES_32 = 32;
	
	public static final int NO_PAGE_REPLACEMENT = 0;	
	public static final int FIFO_PAGE_REPLACEMENT = 1;
	public static final int LRU_PAGE_REPLACEMENT = 2;
	

	public static void main(String[] args) {
		// When running the code for the first time, you will get index out of bounds
		// This is due to that getPageNumber(), getPageOffset() and handlePageFault() is not implemented in MemoryManager.class
		testTaskOne();
		//testTaskTwo(); // Uncomment to run tc
		//testTaskThree();	// Uncomment to run tc
	}
	
	private static void testTaskOne()
	{
		System.out.println("Testing task one\n");
		MemoryManager memoryManager = new MemoryManager(NUMBER_OF_PAGES, PAGE_SIZE, NUMBER_OF_FRAMES_256, BACKING_STORE_FILE, NO_PAGE_REPLACEMENT);
		MemoryProcess mp = new MemoryProcess("C:\\Users\\MEGU0007\\Desktop\\Operating Systems\\Seminar 3\\Seminar_3\\resources/addresses.txt", memoryManager);
		mp.callMemory();
		
		int numberOfPageFaults =  memoryManager.getNumberOfPageFaults();
				
		System.out.println("Number of page faults: " + numberOfPageFaults);
		System.out.println("Expected: " + "244");
		
		if(numberOfPageFaults == 244)
			System.out.println("Pass");
		else
			System.out.println("Assertion Fail");
		
		System.out.println("");
	}
	
	private static void testTaskTwo()
	{
		System.out.println("Testing task two\n");
		int numberOfPageFaults =  getNumberOfPageFaultsFifo(NUMBER_OF_FRAMES_128);
		verifyFifo(numberOfPageFaults, 536, NUMBER_OF_FRAMES_128);			
		
		numberOfPageFaults =  getNumberOfPageFaultsFifo(NUMBER_OF_FRAMES_64);
		verifyFifo(numberOfPageFaults, 759,  NUMBER_OF_FRAMES_64);			
		
		numberOfPageFaults =  getNumberOfPageFaultsFifo(NUMBER_OF_FRAMES_32);
		verifyFifo(numberOfPageFaults, 880,  NUMBER_OF_FRAMES_32);						
	}
	
	private static void testTaskThree()
	{
		System.out.println("Testing task three\n");
		int numberOfPageFaults =  getNumberOfPageFaultsLRU(NUMBER_OF_FRAMES_128);
		verifyLRU(numberOfPageFaults, 534, NUMBER_OF_FRAMES_128);			
		
		numberOfPageFaults =  getNumberOfPageFaultsLRU(NUMBER_OF_FRAMES_64);
		verifyLRU(numberOfPageFaults, 753, NUMBER_OF_FRAMES_64);			
		
		numberOfPageFaults =  getNumberOfPageFaultsLRU(NUMBER_OF_FRAMES_32);
		verifyLRU(numberOfPageFaults, 878, NUMBER_OF_FRAMES_32);			
			
	}
	
	private static void verifyFifo(int numberOfPageFaults, int expectedPageFaults, int numberOfFrames)
	{
		System.out.println("FIFO with " + numberOfFrames + " frames");	
		System.out.println("Number of page faults: " + numberOfPageFaults);
		System.out.println("Expected: " + expectedPageFaults);
		
		if(numberOfPageFaults == expectedPageFaults)
			System.out.println("Pass");
		else
			System.out.println("Assertion Fail");	
		
		System.out.println("");
	}
	
	private static void verifyLRU(int numberOfPageFaults, int expectedPageFaults, int numberOfFrames)
	{
		System.out.println("LRU with " + numberOfFrames + " frames");	
		System.out.println("Number of page faults: " + numberOfPageFaults);
		System.out.println("Expected: " + expectedPageFaults);
		
		if(numberOfPageFaults == expectedPageFaults)
			System.out.println("Pass");
		else
			System.out.println("Assertion Fail");	
		
		System.out.println("");
	}
	
	private static int getNumberOfPageFaultsFifo(int numberOfFrames)
	{
		MemoryManager memoryManager = new MemoryManager(NUMBER_OF_PAGES, PAGE_SIZE, numberOfFrames, BACKING_STORE_FILE, FIFO_PAGE_REPLACEMENT);
		MemoryProcess mp = new MemoryProcess("C:\\Users\\MEGU0007\\Desktop\\Operating Systems\\Seminar 3\\Seminar_3\\resources/addresses.txt", memoryManager);
		mp.callMemory();
		
		return memoryManager.getNumberOfPageFaults();
		
	}
	
	private static int getNumberOfPageFaultsLRU(int numberOfFrames)
	{
		MemoryManager memoryManager = new MemoryManager(NUMBER_OF_PAGES, PAGE_SIZE, numberOfFrames, BACKING_STORE_FILE, LRU_PAGE_REPLACEMENT);
		MemoryProcess mp = new MemoryProcess("C:\\Users\\MEGU0007\\Desktop\\Operating Systems\\Seminar 3\\Seminar_3\\resources/addresses.txt", memoryManager);
		mp.callMemory();
		
		return memoryManager.getNumberOfPageFaults();
		
	}
}
