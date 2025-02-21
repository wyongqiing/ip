package nova.command;

import nova.exception.NovaException;
import nova.task.Task;
import nova.task.TaskList;
import nova.ui.Storage;
import nova.ui.UiNova;

/**
 * Represents a command to unmark a task as not done.
 */
public class UnmarkCommand implements Command {
    private final int index;

    /**
     * Constructs an UnmarkCommand with the specified task index.
     *
     * @param index The index of the task to be unmarked.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the unmark command by marking the specified task as not done,
     * updating the storage, and notifying the user.
     *
     * @param taskList The task list containing the tasks.
     * @param ui       The UI responsible for interacting with the user.
     * @param storage  The storage responsible for saving task changes.
     * @throws NovaException If the specified task index is invalid.
     */
    @Override
    public void execute(TaskList taskList, UiNova ui, Storage storage) throws NovaException {
        Task task = taskList.getTask(index);
        task.markAsNotDone();
        storage.saveTasks(taskList);
        ui.printTaskUpdatedMessage("OK, I've marked this task as not done yet:", task);
    }

    /**
     * Executes the unmark command and returns a confirmation message.
     *
     * @param taskList The task list containing tasks.
     * @param ui       The UI responsible for displaying output.
     * @param storage  The storage component responsible for saving task changes.
     * @return A formatted string confirming the task has been unmarked.
     * @throws NovaException If the specified task index is invalid.
     */
    @Override
    public String executeAndReturn(TaskList taskList, UiNova ui, Storage storage) throws NovaException {
        execute(taskList, ui, storage);
        return "Task unmarked: " + taskList.getTask(index);
    }
}
