package nova.command;

import nova.exception.NovaException;
import nova.task.Task;
import nova.task.TaskList;
import nova.ui.Storage;
import nova.ui.UiNova;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand implements Command {
    private final int index;

    /**
     * Constructs a DeleteCommand with the specified task index.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command by removing a task from the task list,
     * updating storage, and notifying the user.
     *
     * @param taskList The task list from which the task will be removed.
     * @param ui       The UI responsible for user interaction.
     * @param storage  The storage to save the updated task list.
     * @throws NovaException If an error occurs while updating storage.
     */
    @Override
    public void execute(TaskList taskList, UiNova ui, Storage storage) throws NovaException {
        Task removedTask = taskList.removeTask(index);
        storage.saveTasks(taskList);
        ui.printTaskDeletedMessage(removedTask, taskList.getSize());
    }
}