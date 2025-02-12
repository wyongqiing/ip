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

public class AddEventCommand implements Command {
    private final String description;
    private String fromStr;
    private String toStr;

    public AddEventCommand(String description, String from, String to) {
        this.description = description;
        this.fromStr = from;
        this.toStr = to;
    }

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