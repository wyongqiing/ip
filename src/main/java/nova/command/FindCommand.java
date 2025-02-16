package nova.command;

import nova.task.Task;
import nova.task.TaskList;
import nova.ui.Storage;
import nova.ui.UiNova;
import nova.exception.NovaException;

import java.util.List;

/**
 * Represents a command that searches for tasks containing a keyword.
 */
public class FindCommand implements Command {
    private final String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command by searching for matching tasks in the task list.
     *
     * @param taskList The task list to search in.
     * @param ui       The UI for displaying results.
     * @param storage  The storage (not used in this command).
     * @throws NovaException If the keyword is empty.
     */
    @Override
    public void execute(TaskList taskList, UiNova ui, Storage storage) throws NovaException {
        if (keyword.trim().isEmpty()) {
            throw new NovaException("Please provide a keyword to search.");
        }

        List<Task> matchingTasks = taskList.findTasks(keyword);
        ui.printTaskSearchResults(matchingTasks);
    }
}
