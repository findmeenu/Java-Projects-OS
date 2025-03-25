public class StopWatch {

    public static void main(String[] args) {
        // Create a new thread for the stopwatch
        Thread stopwatchThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int time = 0; // Initialize elapsed time to zero

                while (time < 60000) { // Run until 60 seconds (60000 milliseconds)
                    try {
                        Thread.sleep(10); // Pause for 10 milliseconds
                        time += 10; // Increment elapsed time by 10 milliseconds

                        // Print the elapsed time in seconds with two decimal places
                        System.out.printf("Stopwatch thread. Elapsed: %.2f seconds.%n", time / 1000.0);
                    } catch (InterruptedException e) {
                        System.err.println("Stopwatch interrupted: " + e.getMessage());
                        return; // Exit the thread if interrupted
                    }
                }
            }
        });

        System.out.println("Main thread. Waiting for stopwatch thread...");
        System.out.println("Stopwatch thread. Stopwatch starts now!");

        // Start the stopwatch thread
        stopwatchThread.start();

        try {
            // Join the stopwatch thread to the main thread after it completes
            stopwatchThread.join();
        } catch (InterruptedException e) {
            System.err.println("Main thread interrupted while waiting for stopwatch: " + e.getMessage());
        }

        System.out.println("Main thread. Finished stopwatch thread.");
    }
}
