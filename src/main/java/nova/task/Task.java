package nova.task;

public abstract class Task {
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

    public abstract String encode();

    // Decode a task from a string and return a new nova.task.Task object
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

        Task task;
        switch (taskType) {
            case "T":
                task = Todo.decode(encodedTask);
                break;
            case "D":
                task = Deadline.decode(encodedTask);
                break;
            case "E":
                task = Event.decode(encodedTask);
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
