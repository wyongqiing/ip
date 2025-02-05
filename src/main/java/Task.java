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
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    // Decode a task from a string and return a new Task object
    public static Task decode(String encodedTask) {
        String[] parts = encodedTask.split(" \\| ");
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task = new Task(description);
        if (isDone) {
            task.markAsDone();
        }
        return task;
    }


}
