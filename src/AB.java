import ab.command.CommandExecuter;
import ab.entity.BenchmarkEntity;
import ab.service.CommandRunner;

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
            
            BenchmarkEntity benchmarkEntity = new BenchmarkEntity(command, iterations, concurrecy);
            
            CommandExecuter executer = new CommandExecuter(Runtime.getRuntime(), benchmarkEntity.getCommand());
            CommandRunner runner = new CommandRunner(executer, iterations, concurrecy);
            
            runner.run();
            
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

}
