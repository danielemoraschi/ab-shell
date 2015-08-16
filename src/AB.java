import ab.command.ShellCommandExecutor;
import ab.command.ShellCommandRunner;
import ab.entity.Task;
import ab.service.Benchmark;
import ab.service.BenchmarkRunner;
import ab.service.DataCalculator;

/**
 * @author dmoraschi
 *
 */
public class AB {

    /**
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        
        if (args.length < 3) {
            throw new Exception("Invalid number of arguments.");
        }
        
        int iterations = Integer.parseInt(args[0]);
        int concurrecy = Integer.parseInt(args[1]);
        String command = "";
        
        for (int i = 2; i < args.length; i++) {
            command += args[i] + " ";
        }
        
        Task task = new Task(command, iterations, concurrecy);
        
        ShellCommandExecutor executer = new ShellCommandExecutor(task, Runtime.getRuntime());
        ShellCommandRunner runner = new ShellCommandRunner(task, executer);
        
        Benchmark bench = new Benchmark(new DataCalculator());

        
        BenchmarkRunner benchRunner = new BenchmarkRunner(task, bench, runner);
        
        benchRunner.start();

    }

}
