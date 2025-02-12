package nova.task;

import nova.util.DateTimeParser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;
    private static final DateTimeFormatter SAVE_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public LocalDateTime getBy() {
        return by;
    }

    @Override
    public String encode() {
        return "D | " + (isDone() ? "1" : "0") + " | " + getDescription() + " | " + by.format(SAVE_FORMATTER);
    }

    public static Deadline decode(String encodedTask) {
        String[] parts = encodedTask.split("\\s*\\|\\s*");
        if (parts.length < 4) {
            throw new IllegalArgumentException("Invalid encoded deadline task format: " + encodedTask);
        }
        LocalDateTime by = DateTimeParser.parse(parts[3]);
        return new Deadline(parts[2], by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(SAVE_FORMATTER) + ")";
    }

}
