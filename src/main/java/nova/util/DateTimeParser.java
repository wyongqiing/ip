package nova.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class DateTimeParser {
    private static final List<DateTimeFormatter> INPUT_FORMATTERS = Arrays.asList(
            DateTimeFormatter.ofPattern("M/d/yyyy HHmm"),   // e.g., 2/12/2019 1800
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"), // e.g., 2025-02-10 18:00
            DateTimeFormatter.ofPattern("yyyy-MM-dd")       // e.g., 2025-02-10
    );

    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    /**
     * Parses a date-time string into a LocalDateTime object.
     *
     * @param dateTimeStr The date-time string to parse.
     * @return The corresponding LocalDateTime object.
     * @throws DateTimeParseException If the input string cannot be parsed.
     */
    public static LocalDateTime parse(String dateTimeStr) throws DateTimeParseException {
        for (DateTimeFormatter formatter : INPUT_FORMATTERS) {
            try {
                return LocalDateTime.parse(dateTimeStr, formatter);
            } catch (DateTimeParseException ignored) {
                // Try the next format
            }
        }
        throw new DateTimeParseException("Invalid date format. Supported formats: M/d/yyyy HHmm, yyyy-MM-dd HH:mm, yyyy-MM-dd.", dateTimeStr, 0);
    }

    /**
     * Formats a LocalDateTime object into a string for display.
     *
     * @param dateTime The LocalDateTime object to format.
     * @return The formatted date-time string.
     */
    public static String format(LocalDateTime dateTime) {
        return dateTime.format(OUTPUT_FORMATTER);
    }
}
