import ab.command.ShellCommandExecuter;
import ab.entity.Task;
import ab.service.ShellCommandRunner;

/**
 * @author dmoraschi
 *
 */
public class AB {

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
            
            Task task = new Task(command, iterations, concurrecy);
            
            ShellCommandExecuter executer = new ShellCommandExecuter(Runtime.getRuntime(), task.getCommand());
            ShellCommandRunner runner = new ShellCommandRunner(executer, iterations, concurrecy);
            
            runner.run();
            
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

}
