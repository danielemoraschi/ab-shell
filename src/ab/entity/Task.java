/**
 * 
 */
package ab.entity;

/**
 * @author dmoraschi
 *
 */
public class Task {
	
	private Terminal terminal;
    
	/**
	 * 
	 * @param command
	 * @param iterations
	 * @param concurrecy
	 * @throws Exception
	 */
    public Task(Terminal terminal) throws Exception {        
        this.terminal = terminal;
    }
    
    /**
     * 
     * @return
     */
    public int getIterations() {
        return terminal.getIterations();
    }
    
    /**
     * 
     * @return
     */
    public int getConcurrency() {
        return terminal.getConcurrency();
    }
    
    /**
     * 
     * @return
     */
    public String getCommand() {
        //return terminal.getCommand().toString().trim();
        String listString = "";

        for (String s : terminal.getCommand()) {
            listString += s + " ";
        }
        
        return listString.trim();
    }

}
