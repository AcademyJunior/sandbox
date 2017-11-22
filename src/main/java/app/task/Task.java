package app.task;


public class Task {

    private String taskName;
    private String taskDescription;
    private int estimateTime;
    private int estimatePrice;

    public Task(String taskName, String taskDescription, int estimateTime, int estimatePrice) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.estimateTime = estimateTime;
        this.estimatePrice = estimatePrice;
    }


    public String getTaskName() {
        return taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public int getEstimateTime() {
        return estimateTime;
    }

    public int getEstimatePrice() {
        return estimatePrice;
    }

}
