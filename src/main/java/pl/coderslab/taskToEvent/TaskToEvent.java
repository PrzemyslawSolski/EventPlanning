package pl.coderslab.taskToEvent;

import pl.coderslab.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskToEvent{
    private Task task;
    private boolean toAdd;

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public boolean isToAdd() {
        return toAdd;
    }

    public void setToAdd(boolean toAdd) {
        this.toAdd = toAdd;
    }

    @Override
    public String toString() {
        return "TaskToEvent{" +
                "task=" + task +
                ", toAdd=" + toAdd +
                '}';
    }
}
