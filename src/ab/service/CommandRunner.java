package ab.service;

import java.io.IOException;

import ab.command.CommandExecuter;

public class CommandRunner {
    
    CommandExecuter executer;
    Integer iterations;
    Integer concurrecy;
    
    /**
     * 
     * @param iterations
     * @param concurrecy
     * @param command
     * @throws Exception
     */
    public CommandRunner(CommandExecuter executer, Integer iterations, Integer concurrecy) throws Exception {
        if (iterations < 0) {
            throw new Exception("Invalid number value for 'iterations'");
        }
        
        if (concurrecy < 0) {
            throw new Exception("Invalid number value for 'concurrecy'");
        }
        
        this.executer = executer;
        this.iterations = iterations;
        this.concurrecy = concurrecy;
    }
    
    
    /**
     * 
     */
    public void run() {
        while (concurrecy > 0) {
            
            (new Thread() {
                public void run() {
                    Integer conc = concurrecy;
                    Integer intr = iterations;
                    while (intr > 0) {
                        String ret;
                        try {
                            ret = executer.executeCommand().toString();
                        } catch (IOException | InterruptedException e) {
                            ret =  e.getMessage();
                        }
                        
                        //System.out.println("Concurrency: " + conc + ", Iteration: " + intr);
                        //System.out.println(ret);
                        intr--;
                    }
                }
            }).start();
            
            concurrecy--;
        }
    }

}
