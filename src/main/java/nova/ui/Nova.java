package nova.ui;

import nova.command.Command;
import nova.exception.NovaException;
import nova.task.TaskList;

public class Nova {
    private Storage storage;
    private TaskList tasks;
    private UiNova ui;

    public Nova(String filePath) {
        ui = new UiNova();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (NovaException e) {
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.printGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printHorizontalLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (NovaException e) {
                ui.printErrorMessage(e.getMessage());
            } finally {
                ui.printHorizontalLine();
            }
        }
    }

    public static void main(String[] args) {
        new Nova("data/nova.txt").run();
    }
}