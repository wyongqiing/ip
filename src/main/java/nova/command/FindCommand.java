package nova.command;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import nova.exception.NovaException;
import nova.task.Task;
import nova.task.TaskList;
import nova.ui.Storage;
import nova.ui.UiNova;

/**
 * Represents a command that searches for tasks containing a keyword.
 */
public class FindCommand implements Command {
    private static final String ERROR_NULL_KEYWORD = "Keyword cannot be null";

    private final String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        assert keyword != null : ERROR_NULL_KEYWORD;
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

    /**
     * Executes the find command and returns the search results as a formatted string.
     *
     * @param taskList The task list to search in.
     * @param ui       The UI (not used in this method).
     * @param storage  The storage (not used in this method).
     * @return A formatted string containing the matching tasks.
     * @throws NovaException If the keyword is empty.
     */
    @Override
    public String executeAndReturn(TaskList taskList, UiNova ui, Storage storage) throws NovaException {
        List<Task> matchingTasks = taskList.findTasks(keyword);

        if (matchingTasks.isEmpty()) {
            return "No matching tasks found.";
        }

        String result = IntStream.range(0, taskList.getSize())
                .filter(i -> taskList.getTask(i).getDescription().contains(keyword))
                .mapToObj(i -> (i + 1) + ". " + taskList.getTask(i)) // Convert index to 1-based
                .collect(Collectors.joining("\n"));

        return "Here are the matching tasks:\n" + result;
    }
}

