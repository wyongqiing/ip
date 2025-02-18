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

    /**
     * Executes the list command and returns the list of tasks as a formatted string.
     *
     * @param taskList The task list containing the tasks.
     * @param ui       The UI responsible for displaying the tasks.
     * @param storage  The storage (not used in this command but required by the interface).
     * @return A formatted string of all tasks in the list.
     */
    @Override
    public String executeAndReturn(TaskList taskList, UiNova ui, Storage storage) {
        if (taskList.isEmpty()) {
            return "No tasks in your list!";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Here are your tasks:\n");
        for (int i = 0; i < taskList.getSize(); i++) {
            sb.append(i + 1).append(". ").append(taskList.getTask(i)).append("\n");
        }
        return sb.toString().trim(); // Remove trailing newline
    }
}
