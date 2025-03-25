import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class Task1 implements Runnable {
    List<String> input;

    // Constructor to initialize input commands
    public Task1(String command) {
        this.input = Arrays.asList(command.split(" "));
    }

    @Override
    public void run() {    
        ProcessBuilder processBuilder = new ProcessBuilder(input);
        BufferedReader bufferReader = null;
        try {

            Process proc = processBuilder.start();
            InputStream inputStream = proc.getInputStream();
            InputStreamReader isr = new InputStreamReader(inputStream);
            bufferReader = new BufferedReader(isr);

            String line;
            while ((line = bufferReader.readLine()) != null) {
                System.out.println(line );
            }
            //bufferReader.close();
        } catch (java.io.IOException ioe) {
            System.err.println("Error");
            System.err.println(ioe);
        } finally {
            if (bufferReader != null) {
                try {
                    bufferReader.close();
                } 
                catch (java.io.IOException e)
                {
                    System.err.println("Error closing BufferedReader: " + e.getMessage());
                    e.printStackTrace();
                    //if (bufferReader != null) {
                //bufferReader.close();
                }
            }
        }
    }
}



