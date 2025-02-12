package nova.command;

import nova.command.Command;

public class MarkCommand implements Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, UiNova ui, Storage storage) throws NovaException {
        Task task = taskList.getTask(index);
        task.markAsDone();
        storage.saveTasks(taskList);
        ui.printTaskUpdatedMessage("Nice! I've marked this task as done:", task);
    }
}
