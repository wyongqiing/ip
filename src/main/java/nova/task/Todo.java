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
        String[] parts = encodedTask.split("\\s*\\|\\s*");
        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid encoded todo task format: " + encodedTask);
        }
        return new Todo(parts[2]);
    }

    @Override
    public String encode() {
        return "T | " + (isDone()? "1" : "0") + " | " + getDescription();
    }
}
