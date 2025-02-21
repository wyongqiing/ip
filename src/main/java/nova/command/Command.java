package nova.command;

import nova.exception.NovaException;
import nova.task.TaskList;
import nova.ui.Storage;
import nova.ui.UiNova;

/**
 * Represents a command that can be executed within the Nova chatbot.
 * All command classes must implement this interface.
 */
public interface Command {

    /**
     * Executes the command on the provided task list, UI, and storage.
     *
     * @param tasks   The task list on which the command operates.
     * @param ui      The UI responsible for user interaction.
     * @param storage The storage to save the updated task list.
     * @throws NovaException If an error occurs while executing the command.
     */
    void execute(TaskList tasks, UiNova ui, Storage storage) throws NovaException;

    /**
     * Executes the command and returns a response string.
     *
     * @param taskList The list of tasks.
     * @param ui       The UI instance.
     * @param storage  The storage handler.
     * @return Response string for GUI display.
     * @throws NovaException If an error occurs during execution.
     */
    default String executeAndReturn(TaskList taskList, UiNova ui, Storage storage) throws NovaException {
        execute(taskList, ui, storage);
        return "Command executed.";
    }
}
