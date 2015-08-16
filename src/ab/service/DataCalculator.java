package ab.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ab.entity.TaskExecution;

public class DataCalculator {
    
    private List<TaskExecution> tasks = Collections.synchronizedList(new ArrayList<TaskExecution>());
    private int completeRequests = -1;
    private int failedRequests = -1;
    
    /**
     * 
     * @param task
     * @return
     */
    public synchronized boolean addTaskExecution(TaskExecution task) {  
        return tasks.add(task);
    }
    
    /**
     * 
     * @return
     */
    public synchronized int getSize() {
        return tasks.size();
    }
    
    /**
     * 
     * @return
     */
    public synchronized long timeTakenForTests() {
        int size = tasks.size() - 1;
        return tasks.get(size).getEndTime() - tasks.get(0).getStartTime();
    }
    
    /**
     * 
     */
    private synchronized void calculateExitExecutions() {
        completeRequests = 0;
        failedRequests = 0;
        
        for (TaskExecution task : tasks) {
            if (task.isSuccess()) {
                completeRequests++;
            } else {
                failedRequests++;
            }
        }
    }
    
    /**
     * 
     * @return
     */
    public synchronized int completeRequests() {
        calculateExitExecutions();
        return completeRequests;
    }
    
    /**
     * 
     * @return
     */
    public synchronized int failedRequests() {
        calculateExitExecutions();
        return failedRequests;
    }
    
    /**
     * 26.36 [#/sec] (mean)
     */
    public synchronized long requestsPerSecond() {
        long average = 0;
        
        for (TaskExecution task : tasks) {
            long duration = task.getDuration();
            average += 1000 / (duration > 0 ? duration : 1000);
        }
        
        return average / tasks.size();
    }
    
    /**
     * 
     * @return
     */
    public synchronized long timePerRequestAverage() {
        long average = 0;
        
        for (TaskExecution task : tasks) {
            average += task.getDuration();
        }
        
        return average / tasks.size();
    }
    
    /**
     * 
     * @return
     */
    public synchronized long timePerRequestMax() {
        long max = (long) 0;

        for (TaskExecution task : tasks) {
            long duration = task.getDuration();
            if (max < duration) {
                max = duration;
            }
        }
        
        return max;
    }
    
    /**
     * 
     * @return
     */
    public synchronized long timePerRequestMin() {
        long min = 0;

        for (TaskExecution task : tasks) {
            if (min == 0 || min > task.getDuration()) {
                min = task.getDuration();
            }
        }
        
        return min;
    }

}
