package nova.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import nova.util.DateTimeParser;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    private static final String DEADLINE_FORMAT = "yyyy-MM-dd HH:mm";
    private static final DateTimeFormatter SAVE_FORMATTER = DateTimeFormatter.ofPattern(DEADLINE_FORMAT);
    protected LocalDateTime by;

    /**
     * Constructs a Deadline task with the specified description and deadline.
     *
     * @param description The description of the deadline task.
     * @param by          The due date and time of the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Gets the deadline of the task.
     *
     * @return The due date and time.
     */
    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Encodes the task into a string format suitable for storage.
     *
     * @return A string representation of the deadline task for storage.
     */
    @Override
    public String encode() {
        return "D | " + (isDone() ? "1" : "0") + " | " + getDescription() + " | " + by.format(SAVE_FORMATTER);
    }

    /**
     * Decodes an encoded deadline task string into a Deadline object.
     *
     * @param encodedTask The encoded string representing the task.
     * @return The decoded Deadline task.
     * @throws IllegalArgumentException If the encoded string format is invalid.
     */
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
