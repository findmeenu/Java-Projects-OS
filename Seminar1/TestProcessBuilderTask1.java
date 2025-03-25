import java.io.*;
import java.util.*;

public class TestProcessBuilderTask1 {

    static void createProcess(String command) throws java.io.IOException {
        // Create an instance of Task1 with the command
        Task1 myTask1 = new Task1(command);
        // Create a new Thread with the Task1 instance
        Thread myThread = new Thread(myTask1);
         // Start the thread
        myThread.start();
         
    }

    public static void main(String[] args) throws java.io.IOException {
        String commandLine;
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\n***** Welcome to the Java Command Shell *****");
        System.out.println("If you want to exit the shell, type END and press RETURN.\n");
    
        while (true) {
               System.out.print("jsh>");
            commandLine = scanner.nextLine();
            // if user entered a return, just loop again
            if (commandLine.equals("")) {
                continue;
            }
            if (commandLine.toLowerCase().equals("end")) { //User wants to end shell
                System.out.println("\n***** Command Shell Terminated. See you next time. BYE for now. *****\n");
                scanner.close();
                System.exit(0);
            }
            createProcess(commandLine);
        }   
    }
   
}

