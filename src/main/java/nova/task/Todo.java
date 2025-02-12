package nova.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public static Todo decode(String encodedTask) {
        String[] parts = encodedTask.split("\\s*\\|\\s*"); // Split using " | "
        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid encoded todo task format: " + encodedTask);
        }

        boolean isDone = parts[1].trim().equals("1"); // Check if marked done
        String description = parts[2].trim();

        Todo todo = new Todo(description);
        if (isDone) {
            todo.markAsDone(); // Ensure task is marked as done
        }

        return todo;
    }



    @Override
    public String encode() {
        return "T | " + (isDone()? "1" : "0") + " | " + getDescription();
    }
}
