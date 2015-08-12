package abshell;

import abshell.lib.*;

/**
 * @author dmoraschi
 *
 */
public class AbShell {

    /**
     * @param args
     */
    public static void main(String[] args) {
        
        try {
            if (args.length < 3) {
                throw new Exception("Invalid number of arguments.");
            }
            
            Integer iterations = Integer.parseInt(args[0]);
            Integer concurrecy = Integer.parseInt(args[1]);
            String command = "";
            
            for (int i = 2; i < args.length; i++) {
                command += args[i] + " ";
            }
            
            CommandExecuter executer = new CommandExecuter(Runtime.getRuntime(), command);
            CommandRunner runner = new CommandRunner(executer, iterations, concurrecy);
            
            runner.run();
            
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

}
