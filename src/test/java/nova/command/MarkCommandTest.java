package nova.command;

import nova.exception.NovaException;
import nova.task.Task;
import nova.task.TaskList;
import nova.task.Todo;
import nova.ui.Storage;
import nova.ui.UiNova;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MarkCommandTest {
    private TaskList taskList;
    private UiNova ui;
    private Storage storage;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        ui = new UiNova(); // Real UiNova instance
        storage = new Storage("test.txt"); // Temporary storage for testing
    }

    @Test
    public void execute_markAsDone_marksTaskAsDone() throws NovaException {
        // Arrange: Add a task
        Task task = new Todo("Finish homework");
        taskList.addTask(task);

        // Act: Execute MarkCommand
        MarkCommand markCommand = new MarkCommand(0);
        markCommand.execute(taskList, ui, storage);

        // Assert: Check if the task is marked as done
        assertTrue(task.isDone(), "Task should be marked as done.");
    }

    @Test
    public void execute_markAsUndone_marksTaskAsUndone() throws NovaException {
        // Arrange: Add a pre-marked task
        Task task = new Todo("Buy groceries");
        task.markAsDone();
        taskList.addTask(task);

        // Act: Execute MarkCommand
        UnmarkCommand unmarkCommand = new UnmarkCommand(0);
        unmarkCommand.execute(taskList, ui, storage);

        // Assert: Check if the task is marked as NOT done
        assertFalse(task.isDone(), "Task should be marked as not done.");
    }
}