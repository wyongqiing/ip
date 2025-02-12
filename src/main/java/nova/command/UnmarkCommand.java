package nova.command;

import nova.exception.NovaException;
import nova.task.Task;
import nova.task.TaskList;
import nova.ui.Storage;
import nova.ui.UiNova;

public class UnmarkCommand implements Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, UiNova ui, Storage storage) throws NovaException {
        Task task = taskList.getTask(index);
        task.markAsNotDone();
        storage.saveTasks(taskList);
        ui.printTaskUpdatedMessage("OK, I've marked this task as not done yet:", task);
    }
}