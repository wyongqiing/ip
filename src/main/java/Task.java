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
        try {
            // Split the string into parts
            String[] parts = encodedTask.split(" \\| ");

            // Validate the minimum number of parts
            if (parts.length < 3) {
                throw new IllegalArgumentException("Invalid task format: " + encodedTask);
            }

            // Extract task type and details
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            // Handle different task types
            switch (type) {
                case "T": // Todo
                    Todo todo = new Todo(description);
                    if (isDone) todo.markAsDone();
                    return todo;

                case "D": // Deadline
                    if (parts.length < 4) {
                        throw new IllegalArgumentException("Invalid deadline format: " + encodedTask);
                    }
                    String by = parts[3];
                    Deadline deadline = new Deadline(description, by);
                    if (isDone) deadline.markAsDone();
                    return deadline;

                case "E": // Event
                    if (parts.length < 5) {
                        throw new IllegalArgumentException("Invalid event format: " + encodedTask);
                    }
                    String from = parts[3];
                    String to = parts[4];
                    Event event = new Event(description, from, to);
                    if (isDone) event.markAsDone();
                    return event;

                default:
                    throw new IllegalArgumentException("Unknown task type: " + type);
            }
        } catch (Exception e) {
            System.out.println("Error decoding task: " + e.getMessage());
            return null; // Skip invalid tasks
        }
    }

}
