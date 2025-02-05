import java.time.LocalDateTime;

public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // X for done, blank for not done
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }

    public String encode() {
        return "T | " + (isDone? "1" : "0") + " | " + description;
    }

    // Decode a task from a string and return a new Task object
    public static Task decode(String encodedTask) {
        if (encodedTask == null || encodedTask.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid encoded task format: empty or null string");
        }

        String[] parts = encodedTask.split("\\s*\\|\\s*");
        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid encoded task format: " + encodedTask);
        }

        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task = null;
        switch (taskType) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                if (parts.length < 4) {
                    throw new IllegalArgumentException("Invalid encoded deadline task format: " + encodedTask);
                }
                LocalDateTime by = DateTimeParser.parse(parts[3]);
                task = new Deadline(description, by);
                break;
            case "E":
                if (parts.length < 5) {
                    throw new IllegalArgumentException("Invalid encoded event task format: " + encodedTask);
                }
                String from = parts[3];
                String to = parts[4];
                task = new Event(description, from, to);
                break;
            default:
                throw new IllegalArgumentException("Unknown task type: " + taskType);
        }

        if (task != null && isDone) {
            task.markAsDone();
        }
        return task;
    }

}
