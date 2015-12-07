package ab.command;

import ab.entity.Task;
import ab.entity.TaskExecution;
import ab.service.BenchmarkRunner;

public class ShellCommandRunner {
    
    ShellCommandExecutor executor;
    Task task;
    
    /**
     * 
     * @param iterations
     * @param concurrecy
     * @param command
     * @throws Exception
     */
    public ShellCommandRunner(Task task, ShellCommandExecutor executor) throws Exception {
        this.executor = executor;
        this.task = task;
    }
            
    /**
     * 
     */
    public void run(BenchmarkRunner benchmarkRunner) {
        int conc = task.getConcurrency();
        
        while (conc > 0) {
            conc--;
            
            (new Thread("Concurrency: " + conc) {
                
                public void run() {
                    int intr = task.getIterations();
                    
                    while (intr > 0) {
                        try {
                            
                            System.out.println(this.getName() + " int: " + intr);
                            long start = System.currentTimeMillis();
                            
                            //String s = executor.executeCommandAndReturn();
                            //System.out.println(s);
                            
                            int ret = executor.executeCommand();
                            sleep(250);
                            long end = System.currentTimeMillis();
                            
                            TaskExecution taskExec = new TaskExecution(start, end, ret);
                            benchmarkRunner.getBench().registerExecution(taskExec);
                            
                            synchronized(benchmarkRunner) {
                                intr--;
                            }
                            
                        } catch (Exception e) {
                            e.printStackTrace();
                        }                    
                    }
                    
                    synchronized(benchmarkRunner) {
                        benchmarkRunner.notify();
                    }
                }
                
            }).start();
                        
        }
        
    }

}
