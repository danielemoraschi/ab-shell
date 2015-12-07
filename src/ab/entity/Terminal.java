package ab.entity;

import java.util.ArrayList;

import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.spi.StringArrayOptionHandler;

public class Terminal {
    
    @Option(name="-n",aliases="--requests",usage="Number of requests to perform for the benchmarking session. Default is one request.")
    private int iterations;
    
    @Option(name="-c",aliases="--concurrency",usage="Number of multiple requests to perform at a time. Default is one request at a time.")
    private int concurrency;
    
    @Option(name="-cmd",aliases="--command",handler=StringArrayOptionHandler.class,required=true,usage="The shell command to run")
    private ArrayList<String> command;
    
    /**
     * 
     * @return
     */
    public int getIterations() {
        return iterations;
    }
    
    /**
     * 
     * @return
     */
    public int getConcurrency() {
        return concurrency;
    }
    
    /**
     * 
     * @return
     */
    public ArrayList<String> getCommand() {
        return command;
    }
    
    /**
     * @throws Exception 
     * 
     */
    public void validateArguments() throws Exception {
        if (iterations < 0) {
            throw new Exception("Invalid number value for 'iterations'");
        }
        
        if (concurrency < 0) {
            throw new Exception("Invalid number value for 'concurrency'");
        }
        
        if (command.toString().trim().isEmpty()) {
            throw new Exception("Empty command");
        }
    }
    
}
