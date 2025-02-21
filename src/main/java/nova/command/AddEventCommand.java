package nova.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import nova.exception.NovaException;
import nova.task.Event;
import nova.task.Task;
import nova.task.TaskList;
import nova.ui.Storage;
import nova.ui.UiNova;
import nova.util.DateTimeParser;

/**
 * Represents a command that adds an event task to the task list.
 */
public class AddEventCommand implements Command {
    private static final String NULL_DESCRIPTION_ERROR = "Description cannot be null";
    private static final String NULL_START_DATE_ERROR = "Start date cannot be null";
    private static final String NULL_END_DATE_ERROR = "End date cannot be null";
    private static final String INVALID_DATE_ORDER_ERROR = "Start time must be before end time";
    private static final String NULL_PARSED_DATE_ERROR = "Parsed dates should not be null";


    private final String description;
    private String fromString;
    private String toString;

    /**
     * Constructs an AddEventCommand with the given event description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from        The start time of the event in string format.
     * @param to          The end time of the event in string format.
     */
    public AddEventCommand(String description, String from, String to) {
        assert description != null : NULL_DESCRIPTION_ERROR;
        assert from != null : NULL_START_DATE_ERROR;
        assert to != null : NULL_END_DATE_ERROR;
        this.description = description;
        this.fromString = from;
        this.toString = to;
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
            LocalDateTime from = DateTimeParser.parse(fromString);
            LocalDateTime to = DateTimeParser.parse(toString);

            assert from != null && to != null : NULL_PARSED_DATE_ERROR;
            assert !from.isAfter(to) : INVALID_DATE_ORDER_ERROR;
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

    @Override
    public String executeAndReturn(TaskList taskList, UiNova ui, Storage storage) throws NovaException {
        execute(taskList, ui, storage);
        return "Added event: [E][ ] " + description + " (from: " + fromString + " to: " + toString + ")";
    }
}
