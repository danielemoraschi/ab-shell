package ab.entity;

/**
 *
 */
public class TaskExecution {

    private long startAt;
    private long endAt;
    private long duration;
    private boolean success;

    /**
     *
     * @param startAt
     * @param endAt
     * @param exitCode
     * @throws Exception
     */
    public TaskExecution(long startAt, long endAt, int exitCode) throws Exception {
        if (startAt > endAt) {
            throw new Exception("Start/End time mismatch.");
        }
        
        if (exitCode < 0) {
            throw new Exception("Invalid exit code: \"" + exitCode + "\".");
        }

        this.startAt = startAt;
        this.endAt = endAt;
        this.duration = endAt - startAt;
        this.success = exitCode == 0;
    }

    /**
     *
     * @return
     */
    public long getStartTime() {
        return startAt;
    }

    /**
     *
     * @return
     */
    public long getEndTime() {
        return endAt;
    }

    /**
     *
     * @return
     */
    public long getDuration() {
        return this.duration;
    }

    /**
     *
     * @return
     */
    public boolean isSuccess() {
        return success;
    }
    
}
