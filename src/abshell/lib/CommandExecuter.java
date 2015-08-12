package abshell.lib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author dmoraschi
 *
 */
public class CommandExecuter {
    
    Runtime runtime;
    String command;
    
    /**
     * 
     * @param command
     * @throws Exception
     */
    public CommandExecuter(Runtime runtime, String command) throws Exception {
        if (command.trim().isEmpty()) {
            throw new Exception("Empty command");
        }
        
        this.runtime = runtime;
        this.command = command.trim();
    }
    
    /**
     * 
     * @return
     * @throws IOException
     */
    private Process execute() throws IOException {
        return runtime.exec(command);
    }
    
    /**
     * 
     * @return
     * @throws IOException
     * @throws InterruptedException 
     */
    public Integer executeCommand() throws IOException, InterruptedException {
        return this.execute().waitFor();
    }

    /**
     * 
     * @return
     * @throws InterruptedException
     * @throws IOException
     */
    public String executeCommandAndReturn() throws InterruptedException, IOException {
        Process p = this.execute();
        p.waitFor();
        
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(p.getInputStream()));

        String line = "";
        StringBuffer output = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            output.append(line + "\n");
        }

        return output.toString();
    }

}
