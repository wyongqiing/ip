package nova.command;

public interface Command {
    void execute(TaskList tasks, UiNova ui, Storage storage) throws NovaException;

    default boolean isExit() {
        return false; // Default implementation: most commands are not exit commands
    }
}
