package nova.command;

import nova.task.TaskList;
import nova.ui.Storage;
import nova.ui.UiNova;

/**
 * Represents a command that lists all tasks in the task list.
 */
public class ListCommand implements Command {

    /**
     * Executes the list command by displaying all tasks in the task list.
     *
     * @param taskList The task list containing the tasks.
     * @param ui       The UI responsible for displaying the tasks to the user.
     * @param storage  The storage (not used in this command but required by the interface).
     */
    @Override
    public void execute(TaskList taskList, UiNova ui, Storage storage) {
        ui.printTaskList(taskList);
    }
}
