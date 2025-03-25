import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.io.File;
import java.io.FileWriter;

public class Task2 implements Runnable {
    List<String> input;
    String maincommand;

    // Constructor to initialize input commands
    public Task2(String command) {
        this.input = Arrays.asList(command.split(" "));
        maincommand = command;
    }

    @Override
    public void run() {    
        ProcessBuilder processBuilder = new ProcessBuilder(input);
        BufferedReader bufferReader = null;
//------------------------------------//
        //NEW ADD START
        BufferedWriter errorWriter = null;
        //NEW ADD END
//______________________________________________...................................//
        try {
            Process proc = processBuilder.start();
            // Reads raw bytes of data from a source like a file or console
            InputStream inputStream = proc.getInputStream();
            // Converts bytes into characters like binary to text
            InputStreamReader isr = new InputStreamReader(inputStream);
            // Read characters from files
            bufferReader = new BufferedReader(isr);

            String line;
            while ((line = bufferReader.readLine()) != null) {
                System.out.println(line );
            }

        } catch (java.io.IOException ioe) 
        {
            System.err.println("Error");
            //System.err.println(ioe);
//---------------------------------------------
//NEW ADD HERE            
                try
                {
                    //System.out.println(maincommand);
                    if (maincommand.toLowerCase() != "showerrlog")
                    {
                        String filePath = "error13.log";
                        File errorLogFile = new File(filePath); 
                        
                        if (!errorLogFile.exists()) 
                        {
                            boolean created = errorLogFile.createNewFile();
                            
                            if (created) {
                                System.out.println("Created error log file: " + filePath);
                            } else {
                                System.err.println("Failed to create error log file: " + filePath);
                            }
                        }
                        

                        errorWriter = new BufferedWriter(new FileWriter(errorLogFile, true));
                        errorWriter.write(maincommand);
                        errorWriter.newLine(); // Write a new line after each input 
                        errorWriter.flush();
                    }
                } 
                catch (IOException e) 
                {
                    e.printStackTrace();
                }
            } /*finally {
                // Close errorWriter if it was opened
                if (errorWriter != null) 
                {
                    try {
                        errorWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }*/
//NEW ADD END
//_____________________________________________________________________________________________//            
          finally {
            if (errorWriter != null) {
                try {
                    errorWriter.close();
                } catch (IOException e) {
                    System.err.println("Error closing errorWriter: " + e.getMessage());
                    e.printStackTrace();
                }
            }

            if (bufferReader != null) {
                try {
                    bufferReader.close();
                } 
                catch (java.io.IOException e)
                {
                    System.err.println("Error closing BufferedReader: " + e.getMessage());
                    e.printStackTrace();
                }
            }

            
        }
    }
}