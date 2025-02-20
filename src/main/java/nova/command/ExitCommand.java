package nova.command;

import nova.task.TaskList;
import nova.ui.Storage;
import nova.ui.UiNova;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand implements Command {

    /**
     * Executes the exit command by printing a farewell message
     * and terminating the program.
     *
     * @param taskList The task list (not used in this command).
     * @param ui       The UI responsible for displaying the farewell message.
     * @param storage  The storage (not used in this command).
     */
    @Override
    public void execute(TaskList taskList, UiNova ui, Storage storage) {
        ui.printFarewell();
        System.exit(0);
    }
    @Override
    public String executeAndReturn(TaskList taskList, UiNova ui, Storage storage) {
        return "Goodbye! See you next time!";
    }
}
