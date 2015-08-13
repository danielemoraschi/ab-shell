/**
 * 
 */
package abshell.lib;

/**
 * @author dmoraschi
 *
 */
public class BenchmarkEntity {
	
	private Integer iterations;
	private Integer concurrecy;
	private String command;
    
	/**
	 * 
	 * @param command
	 * @param iterations
	 * @param concurrecy
	 * @throws Exception
	 */
    public BenchmarkEntity(String command, Integer iterations, Integer concurrecy) throws Exception {
        if (iterations < 0) {
            throw new Exception("Invalid number value for 'iterations'");
        }
        
        if (concurrecy < 0) {
            throw new Exception("Invalid number value for 'concurrecy'");
        }
        
        if (command.trim().isEmpty()) {
            throw new Exception("Empty command");
        }
        
        this.iterations = iterations;
        this.concurrecy = concurrecy;
        this.command = command.trim();
    }
    
    /**
     * 
     * @return
     */
    public Integer getIterations() {
    	return iterations;
    }
    
    /**
     * 
     * @return
     */
    public Integer getConcurrecy() {
    	return concurrecy;
    }
    
    /**
     * 
     * @return
     */
    public String getCommand() {
    	return command;
    }

}
