package nova.command;

import nova.task.TaskList;
import nova.ui.UiNova;
import nova.ui.Storage;
import nova.exception.NovaException;

public interface Command {
    void execute(TaskList tasks, UiNova ui, Storage storage) throws NovaException;

    default boolean isExit() {
        return false; // Default implementation: most commands are not exit commands
    }
}
