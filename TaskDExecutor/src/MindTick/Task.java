package MindTick;

/**
 * Created by dileep.kumar on 8/3/17.
 */
public class Task implements ITask {

    private String taskId;

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskId() {
        return taskId;
    }

    @Override
    public void execute() {
        System.out.println(taskId + " finished.");
    }
}
