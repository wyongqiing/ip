package nova.task;

import nova.exception.NovaException;


import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task removeTask(int index) throws NovaException {
        validateIndex(index);
        return tasks.remove(index);
    }

    public Task getTask(int index) throws NovaException {
        validateIndex(index);
        return tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }

    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    private void validateIndex(int index) throws NovaException {
        if (index < 0 || index >= tasks.size()) {
            throw new NovaException("nova.task.Task number is invalid or out of range.");
        }
    }
}



