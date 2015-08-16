package ab.service;

import ab.entity.TaskExecution;

public class Benchmark {
    
    private DataCalculator calculator;
    private long startAt;
    private long endAt;
    
    public Benchmark(DataCalculator calculator) {
        this.calculator = calculator;
    }
        
    public void start() {
        startAt = System.currentTimeMillis();
    }
    
    public void end() throws Exception {
        if (isEnded()) {
            throw new Exception("Benchmark already ended at: " + endAt);
        }
        
        endAt = System.currentTimeMillis();
    }
    
    public boolean isStarted() {
        return startAt == 0 ? false : true;
    }
    
    public boolean isEnded() {
        return !(endAt == 0 | !isStarted());
    }
    
    public synchronized void registerExecution(TaskExecution task) throws Exception {
        if (!isStarted()) {
            throw new Exception("Unable to add Execution, Benchmark not started yet.");
        }
        
        calculator.addTaskExecution(task);
    }

    public long getDuration() {
        return endAt - startAt;
    }
    
    public DataCalculator getData() throws Exception {
        if (!isStarted()) {
            throw new Exception("Benchmark not started yet.");
        }
        
        if (!isEnded()) {
            throw new Exception("Benchmark not ended yet.");
        }
        
        return calculator;
    }
        
}
