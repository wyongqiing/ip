public class ExitCommand implements Command {

    @Override
    public void execute(TaskList taskList, UiNova ui, Storage storage) {
        ui.printFarewell();
        System.exit(0);
    }

    @Override
    public boolean isExit() {
        return true; // Signals that this command will terminate the program
    }
}