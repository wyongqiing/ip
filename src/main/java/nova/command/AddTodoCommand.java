package nova.command;

import nova.exception.NovaException;
import nova.task.Task;
import nova.task.TaskList;
import nova.task.Todo;
import nova.ui.Storage;
import nova.ui.UiNova;

public class AddTodoCommand implements Command {

    private String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

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
