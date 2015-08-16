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

    public void run() {
        bench.start();
        
        int intr = task.getIterations();
        int cc = task.getConcurrency();
        int tot = cc*intr;
        
        while (tot  > 0) {
            synchronized (this) {
                try {
                    runner.run(this);
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            tot--;
        }
        
        
        try {
            bench.end();
            
            System.out.println("timeTakenForTests: " + bench.getData().timeTakenForTests());
            System.out.println("totalRequest: " + bench.getData().getSize());
            System.out.println("completeRequests: " + bench.getData().completeRequests());
            System.out.println("failedRequests: " + bench.getData().failedRequests());
            System.out.println("requestsPerSecond: " + bench.getData().requestsPerSecond());
            System.out.println("timePerRequestAverage: " + bench.getData().timePerRequestAverage());
            System.out.println("timePerRequestMax: " + bench.getData().timePerRequestMax());
            System.out.println("timePerRequestMin: " + bench.getData().timePerRequestMin());

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}