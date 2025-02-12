public class DeleteCommand implements Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, UiNova ui, Storage storage) throws NovaException {
        Task removedTask = taskList.removeTask(index);
        storage.saveTasks(taskList);
        ui.printTaskDeletedMessage(removedTask, taskList.getSize());
    }
}