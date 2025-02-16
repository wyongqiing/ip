package nova.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import nova.exception.NovaException;
import nova.task.Task;
import nova.task.Event;
import nova.task.TaskList;
import nova.ui.Storage;
import nova.ui.UiNova;
import nova.util.DateTimeParser;

/**
 * Represents a command that adds an event task to the task list.
 */
public class AddEventCommand implements Command {
    private final String description;
    private String fromStr;
    private String toStr;

    /**
     * Constructs an AddEventCommand with the given event description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from        The start time of the event in string format.
     * @param to          The end time of the event in string format.
     */
    public AddEventCommand(String description, String from, String to) {
        this.description = description;
        this.fromStr = from;
        this.toStr = to;
    }

    /**
     * Executes the command by creating a new event and adding it to the task list.
     * It also saves the updated task list to storage and notifies the user.
     *
     * @param taskList The task list to which the event is added.
     * @param ui       The UI for user interaction.
     * @param storage  The storage to save the updated task list.
     * @throws NovaException If there is a formatting issue with the date/time or the start time is after the end time.
     */
    @Override
    public void execute(TaskList taskList, UiNova ui, Storage storage) throws NovaException {
        try {
            LocalDateTime from = DateTimeParser.parse(fromStr);
            LocalDateTime to = DateTimeParser.parse(toStr);
            if (from.isAfter(to)) {
                throw new NovaException("'from' date must be before 'to' date.");
            }
            Task task = new Event(description, from, to);
            taskList.addTask(task);
            storage.saveTasks(taskList);
            ui.printTaskAddedMessage(task, taskList.getSize());
        } catch (DateTimeParseException e) {
            throw new NovaException("Invalid date format! Supported formats: M/d/yyyy HHmm, yyyy-MM-dd HH:mm, yyyy-MM-dd.");
        }
    }
}