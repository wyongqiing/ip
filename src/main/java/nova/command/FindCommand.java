package nova.command;

import java.util.List;
import java.util.stream.Collectors;

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
        this.keyword = keyword.toLowerCase();
    }

    /**
     * Executes the find command by searching for matching tasks in the task list.
     *
     * @param taskList The task list to search in.
     * @param ui       The UI for displaying results.
     * @param storage  The storage (not used in this command).
     */
    @Override
    public void execute(TaskList taskList, UiNova ui, Storage storage) {
        List<Task> matchingTasks = findMatchingTasks(taskList);

        if (matchingTasks.isEmpty()) {
            ui.printMessage("No tasks found matching: " + keyword);
        } else {
            ui.printTaskSearchResults(matchingTasks);
        }
    }
    /**
     * Executes the find command and returns the search results as a formatted string.
     *
     * @param taskList The task list to search in.
     * @param ui       The UI (not used in this method).
     * @param storage  The storage (not used in this method).
     * @return A formatted string containing the matching tasks.
     */
    @Override
    public String executeAndReturn(TaskList taskList, UiNova ui, Storage storage) {
        List<Task> matchingTasks = findMatchingTasks(taskList);

        if (matchingTasks.isEmpty()) {
            return "No matching tasks found for: " + keyword;
        }

        return formatSearchResults(matchingTasks);
    }

    /**
     * Finds tasks that contain the keyword in either their description or tags.
     *
     * @param taskList The task list to search through.
     * @return A list of tasks that match the search criteria.
     */
    private List<Task> findMatchingTasks(TaskList taskList) {
        return taskList.getTasks().stream()
                .filter(task -> task.getDescription().toLowerCase().contains(keyword))
                .collect(Collectors.toList());
    }

    /**
     * Formats the search results into a numbered list.
     *
     * @param matchingTasks The list of matching tasks.
     * @return A formatted string of search results.
     */
    private String formatSearchResults(List<Task> matchingTasks) {
        StringBuilder result = new StringBuilder("Here are the matching tasks:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            result.append(i + 1).append(". ").append(matchingTasks.get(i)).append("\n");
        }
        return result.toString();
    }

}

