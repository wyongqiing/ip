package nova.command;

import nova.task.TaskList;
import nova.ui.Storage;
import nova.ui.UiNova;

/**
 * Represents a command that displays available commands.
 */
public class HelpCommand implements Command {

    /**
     * Returns a formatted string listing all available commands in Nova Chatbot.
     *
     * @return A help message containing a list of commands and their descriptions.
     */
    public String help() {
        return "Available Commands:\n"
                + "-------------------\n"
                + "HELP      - Displays the list of all commands.\n"
                + "FIND      - Find tasks based on keyword. Usage: find <keyword>\n"
                + "MARK      - Marks a task as completed. Usage: mark <task number>\n"
                + "UNMARK    - Unmarks a completed task. Usage: unmark <task number>\n"
                + "TODO      - Adds a todo. Usage: todo <task>\n"
                + "DEADLINE  - Adds a deadline. Usage: deadline <task> /by <date>\n"
                + "EVENT     - Adds an event. Usage: event <task> /from <start> /to <end>\n"
                + "LIST      - Displays all tasks.\n"
                + "DELETE    - Deletes a task. Usage: delete <task number>\n"
                + "EXIT      - Closes the chatbot.\n"
                + "-------------------\n";
    }

    /**
     * Executes the help command by displaying the help message in the UI.
     *
     * @param taskList The task list (not used in this command).
     * @param ui       The UI responsible for displaying the message.
     * @param storage  The storage (not used in this command).
     */
    @Override
    public void execute(TaskList taskList, UiNova ui, Storage storage) {
        ui.printMessage(help());
    }

    /**
     * Executes the help command and returns the help message as a string.
     *
     * @param taskList The task list.
     * @param ui       The UI.
     * @param storage  The storage.
     * @return A string containing the help message.
     */
    @Override
    public String executeAndReturn(TaskList taskList, UiNova ui, Storage storage) {
        return help();
    }
}

