package nova.command;

import nova.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddTodoCommandTest {
    @Test
    public void testTodoCreation() {
        Todo todo = new Todo("Read book");
        assertEquals("[T][ ] Read book", todo.toString(), "Todo string representation is incorrect.");
    }

    @Test
    public void testMarkAsDone() {
        Todo todo = new Todo("Write report");
        todo.markAsDone();
        assertEquals("[T][X] Write report", todo.toString(), "Marking todo as done failed.");
    }

    @Test
    public void testMarkAsNotDone() {
        Todo todo = new Todo("Submit assignment");
        todo.markAsDone();
        todo.markAsNotDone();
        assertEquals("[T][ ] Submit assignment", todo.toString(), "Unmarking todo as not done failed.");
    }

    @Test
    public void testEncoding() {
        Todo todo = new Todo("Finish coding");
        assertEquals("T | 0 | Finish coding", todo.encode(), "Encoding format is incorrect.");
        todo.markAsDone();
        assertEquals("T | 1 | Finish coding", todo.encode(), "Encoding format after marking as done is incorrect.");
    }

    @Test
    public void testDecoding() {
        Todo todo = Todo.decode("T | 1 | Exercise");
        assertEquals("[T][X] Exercise", todo.toString(), "Decoding failed.");
    }
}
