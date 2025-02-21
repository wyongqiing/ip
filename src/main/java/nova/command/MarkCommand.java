package nova.command;

import nova.exception.NovaException;
import nova.task.Task;
import nova.task.TaskList;
import nova.ui.Storage;
import nova.ui.UiNova;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand implements Command {
    private final int index;

    /**
     * Constructs a MarkCommand with the specified task index.
     *
     * @param index The index of the task to be marked as done (zero-based).
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to mark a task as done.
     *
     * @param taskList The list of tasks.
     * @param ui       The user interface to display messages.
     * @param storage  The storage handler to save updated tasks.
     * @throws NovaException If the task index is invalid.
     */
    @Override
    public void execute(TaskList taskList, UiNova ui, Storage storage) throws NovaException {
        Task task = taskList.getTask(index);
        task.markAsDone();
        storage.saveTasks(taskList);
        ui.printTaskUpdatedMessage("Nice! I've marked this task as done:", task);
    }

    /**
     * Executes the mark command and returns a confirmation message.
     *
     * @param taskList The task list containing tasks.
     * @param ui       The UI responsible for displaying output.
     * @param storage  The storage component responsible for saving task changes.
     * @return A formatted string confirming the task has been marked as done.
     * @throws NovaException If the specified task index is invalid.
     */
    @Override
    public String executeAndReturn(TaskList taskList, UiNova ui, Storage storage) throws NovaException {
        execute(taskList, ui, storage);
        return "Task marked as done: " + taskList.getTask(index);
    }
}
