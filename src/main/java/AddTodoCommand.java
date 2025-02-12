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
