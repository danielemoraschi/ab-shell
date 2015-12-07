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

    /**
     * 
     * @return
     */
    public long getDuration() {
        return endAt - startAt;
    }
    
    /**
     * 
     * @return DataCalculator
     * @throws Exception
     */
    public DataCalculator getData() throws Exception {
        if (!isStarted()) {
            throw new Exception("Benchmark not started yet.");
        }
        
        if (!isEnded()) {
            throw new Exception("Benchmark not ended yet.");
        }
        
        return calculator;
    }
    
    /**
     * @throws Exception
     */
    public void printStats() throws Exception {
        if (!isStarted()) {
            throw new Exception("Benchmark not started yet.");
        }
        
        if (!isEnded()) {
            throw new Exception("Benchmark not ended yet.");
        }
        
        System.out.println("timeTakenForTests: " + calculator.timeTakenForTests());
        System.out.println("totalRequest: " + calculator.getSize());
        System.out.println("completeRequests: " + calculator.completeRequests());
        System.out.println("failedRequests: " + calculator.failedRequests());
        System.out.println("requestsPerSecond: " + calculator.requestsPerSecond());
        System.out.println("timePerRequestAverage: " + calculator.timePerRequestAverage());
        System.out.println("timePerRequestMax: " + calculator.timePerRequestMax());
        System.out.println("timePerRequestMin: " + calculator.timePerRequestMin());
    }
        
}
