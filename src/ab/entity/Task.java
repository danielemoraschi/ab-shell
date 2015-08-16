/**
 * 
 */
package ab.entity;

/**
 * @author dmoraschi
 *
 */
public class Task {
	
	private int iterations;
	private int concurrency;
	private String command;
    
	/**
	 * 
	 * @param command
	 * @param iterations
	 * @param concurrecy
	 * @throws Exception
	 */
    public Task(String command, int iterations, int concurrency) throws Exception {
        if (iterations < 0) {
            throw new Exception("Invalid number value for 'iterations'");
        }
        
        if (concurrency < 0) {
            throw new Exception("Invalid number value for 'concurrency'");
        }
        
        if (command.trim().isEmpty()) {
            throw new Exception("Empty command");
        }
        
        this.iterations = iterations;
        this.concurrency = concurrency;
        this.command = command.trim();
    }
    
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
    public String getCommand() {
        return command;
    }

}
