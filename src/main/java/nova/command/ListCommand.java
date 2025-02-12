package nova.command;

import nova.task.TaskList;
import nova.ui.Storage;
import nova.ui.UiNova;

public class ListCommand implements Command {

    @Override
    public void execute(TaskList taskList, UiNova ui, Storage storage) {
        ui.printTaskList(taskList);
    }
}
