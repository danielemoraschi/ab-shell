package ab.service;

import ab.command.ShellCommandRunner;
import ab.entity.Task;

public class BenchmarkRunner extends Thread {

    Task task;
    Benchmark bench;
    ShellCommandRunner runner;
    
    public BenchmarkRunner(Task task, Benchmark benchmark, ShellCommandRunner runner) {
        this.task = task;
        this.runner = runner;
        this.bench = benchmark;
    }
    
    public Benchmark getBench() {
        return bench;
    }

    /**
     * 
     */
    public void run() {
        try {
           
            bench.start();
            runner.run(this);
            
            synchronized (this) {
                wait();
            }
            
            bench.end();
            bench.printStats();
            
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }        
    }

}