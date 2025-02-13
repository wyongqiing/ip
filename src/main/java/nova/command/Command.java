package nova.command;

import nova.task.TaskList;
import nova.ui.UiNova;
import nova.ui.Storage;
import nova.exception.NovaException;

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
     * Determines whether this command signals the termination of the program.
     * By default, most commands do not exit the program.
     *
     * @return {@code true} if the command is an exit command, {@code false} otherwise.
     */
    default boolean isExit() {
        return false; // Default implementation: most commands are not exit commands
    }
}
