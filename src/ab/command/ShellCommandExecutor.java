package ab.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ab.entity.Task;

/**
 * @author dmoraschi
 *
 */
public class ShellCommandExecutor {
    
    Runtime runtime;
    String command;
    
    /**
     * 
     * @param command
     * @throws Exception
     */
    public ShellCommandExecutor(Task task, Runtime runtime) throws Exception {
        this.runtime = runtime;
        this.command = task.getCommand();
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

        String line;
        StringBuffer output = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            output.append(line + "\n");
        }

        return output.toString();
    }

}
