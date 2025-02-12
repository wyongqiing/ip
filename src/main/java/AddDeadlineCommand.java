import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class AddDeadlineCommand implements Command {
    private final String description;
    private final String byStr;

    /**
     * Constructor for AddDeadlineCommand.
     *
     * @param description The description of the deadline task.
     * @param byStr       The deadline in string format.
     */
    public AddDeadlineCommand(String description, String byStr) {
        this.description = description;
        this.byStr = byStr;
    }

    /**
     * Executes the AddDeadlineCommand by adding a deadline task to the task list.
     *
     * @param taskList The task list to which the task is added.
     * @param ui       The UI to interact with the user.
     * @param storage  The storage to save the updated task list.
     * @throws NovaException If the deadline format is invalid or there is an issue saving tasks.
     */
    @Override
    public void execute(TaskList taskList, UiNova ui, Storage storage) throws NovaException {
        try {
            // Parse the deadline string into a LocalDateTime object
            LocalDateTime by = DateTimeParser.parse(byStr);

            // Create a new Deadline task and add it to the task list
            Task task = new Deadline(description, by);
            taskList.addTask(task);

            // Save the updated task list to storage
            storage.saveTasks(taskList);

            // Notify the user
            ui.printTaskAddedMessage(task, taskList.getSize());
        } catch (DateTimeParseException e) {
            throw new NovaException("Invalid date format! Supported formats: M/d/yyyy HHmm, yyyy-MM-dd HH:mm, yyyy-MM-dd.");
        }
    }
}