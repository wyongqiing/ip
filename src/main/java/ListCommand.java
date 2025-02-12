public class ListCommand implements Command {

    @Override
    public void execute(TaskList taskList, UiNova ui, Storage storage) {
        ui.printTaskList(taskList);
    }
}
