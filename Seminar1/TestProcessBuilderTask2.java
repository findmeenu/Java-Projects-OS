import java.io.*;
import java.util.*;
import java.io.File;


public class TestProcessBuilderTask2 {

    static void createProcess(String command) throws java.io.IOException {
        // Create an instance of Task1 with the command
        Task2 myTask2 = new Task2(command);
        // Create a new Thread with the Task1 instance
        Thread myThread = new Thread(myTask2);
         // Start the thread
        myThread.start();
         
    }

    public static void main(String[] args) throws java.io.IOException {
        String commandLine;
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\n***** Welcome to the Java Command Shell *****");
        System.out.println("If you want to exit the shell, type END and press RETURN.\n");
        
        String filePath = "error13.log";
        File errorLogFile = new File(filePath); 
        while (true) 
        {
            System.out.print("jsh>");
         commandLine = scanner.nextLine();
         // if user entered a return, just loop again
            if (commandLine.equals("")) 
            {
                continue;
            }
            if (commandLine.toLowerCase().equals("end")) 
            { //User wants to end shell
                System.out.println("\n***** Command Shell Terminated. See you next time. BYE for now. *****\n");
                
                BufferedWriter errorWriter = new BufferedWriter(new FileWriter(filePath));
                errorWriter.write("");
                errorWriter.close();

                scanner.close();
                System.exit(0);

            }
         //NEW PART ADDED//
            if (commandLine.toLowerCase().equals("showerrlog"))
            {
                // Create a BufferedReader to read from the file
                

                try (BufferedReader reader = new BufferedReader(new FileReader(errorLogFile)))
                {

                    String line;
                    // Read and print each line from the file
                    while ((line = reader.readLine()) != null) 
                    {
                        System.out.println(line);
                    }        
                }   
                catch (IOException e) 
                {
                        e.printStackTrace();
                        System.out.println(commandLine);
                }
            //NEW ADD ENDS HERE//
            //_________________________________________________________________________________
            
            }   
            
            createProcess(commandLine);
        }
   
    }
}
