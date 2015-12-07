import java.util.ArrayList;
import java.util.Arrays;

import ab.command.ShellCommandExecutor;
import ab.command.ShellCommandRunner;
import ab.entity.Task;
import ab.entity.Terminal;
import ab.service.Benchmark;
import ab.service.BenchmarkRunner;
import ab.service.DataCalculator;

import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.ParserProperties;
import org.kohsuke.args4j.spi.OptionHandler;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

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
        
        Terminal terminal = new Terminal();
        CmdLineParser parser = new CmdLineParser(terminal);
        
        try {
            parser.parseArgument(args);
            terminal.validateArguments();
            
        } catch (CmdLineException e) {
            // handling of wrong arguments
            parser.printUsage(System.err);
        }
                
        Task task = new Task(terminal);
        
        ShellCommandExecutor executer = new ShellCommandExecutor(task, Runtime.getRuntime());
        ShellCommandRunner runner = new ShellCommandRunner(task, executer);
        
        Benchmark bench = new Benchmark(new DataCalculator());

        
        BenchmarkRunner benchRunner = new BenchmarkRunner(task, bench, runner);
        
        benchRunner.start();
    }

}
