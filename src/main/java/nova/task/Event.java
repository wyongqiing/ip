package nova.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import nova.util.DateTimeParser;

public class Event extends Task {
    private static final DateTimeFormatter SAVE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + DateTimeParser.format(from) + " to: " + DateTimeParser.format(to) + ")";
    }

    @Override
    public String encode() {
        return "E | " + (isDone() ? "1" : "0") + " | " + getDescription() + " | " + from.format(SAVE_FORMATTER) + " | " + to.format(SAVE_FORMATTER);
    }

    public static Event decode(String encoded) {
        String[] parts = encoded.split(" \\| ");
        if (parts.length != 5 || !parts[0].equals("E")) {
            throw new IllegalArgumentException("Invalid encoded event format.");
        }
        boolean isDone = parts[1].equals("1");
        String description = parts[2];
        LocalDateTime from = DateTimeParser.parse(parts[3]);
        LocalDateTime to = DateTimeParser.parse(parts[4]);
        Event event = new Event(description, from, to);
        if (isDone) {
            event.markAsDone();
        }
        return event;
    }
}
