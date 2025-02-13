package nova.command;

import nova.exception.NovaException;
import nova.task.Task;
import nova.task.TaskList;
import nova.task.Todo;
import nova.ui.Storage;
import nova.ui.UiNova;

/**
 * Represents a command to add a Todo task to the task list.
 */
public class AddTodoCommand implements Command {

    private String description;

    /**
     * Constructs an AddTodoCommand with the given task description.
     *
     * @param description The description of the todo task.
     */
    public AddTodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the AddTodoCommand by adding a new todo task to the task list.
     * It also saves the updated task list and notifies the user.
     *
     * @param taskList The task list where the new todo task will be added.
     * @param ui       The UI to display messages to the user.
     * @param storage  The storage to save the updated task list.
     * @throws NovaException If the description is empty or an error occurs while saving.
     */
    @Override
    public void execute(TaskList taskList, UiNova ui, Storage storage) throws NovaException {
        if (description.isEmpty()) {
            throw new NovaException("The description of a todo cannot be empty.");
        }
        Task task = new Todo(description);
        taskList.addTask(task);
        storage.saveTasks(taskList);
        ui.printTaskAddedMessage(task, taskList.getSize());
    }

}
