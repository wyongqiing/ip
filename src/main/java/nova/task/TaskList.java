package nova.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import nova.exception.NovaException;

/**
 * Represents a list of tasks and provides methods to manipulate the tasks.
 */
public class TaskList {
    private static final String INVALID_INDEX_ERROR = "Index must be non-negative";
    private final List<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the given list of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Adds a new task to the list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the list at the specified index.
     *
     * @param index The index of the task to be removed.
     * @return The removed task.
     * @throws NovaException If the index is out of range.
     */
    public Task removeTask(int index) throws NovaException {
        validateIndex(index);
        return tasks.remove(index);
    }

    /**
     * Retrieves a task from the list at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     * @throws NovaException If the index is out of range.
     */
    public Task getTask(int index) throws NovaException {
        validateIndex(index);
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns a copy of the list of tasks.
     *
     * @return A list containing all tasks.
     */
    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    /**
     * Checks if the task list is empty.
     *
     * @return True if the list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Validates if the provided index is within bounds.
     *
     * @param index The index to validate.
     * @throws NovaException If the index is out of range.
     */
    private void validateIndex(int index) throws NovaException {
        assert index >= 0 : INVALID_INDEX_ERROR;
        if (index < 0 || index >= tasks.size()) {
            throw new NovaException("nova.task.Task number is invalid or out of range.");
        }
    }

    public List<Task> findTasks(String keyword) {
        return tasks.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }
}



