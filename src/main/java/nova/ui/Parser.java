package nova.ui;

import nova.command.*;
import nova.exception.NovaException;

public class Parser {

    /**
     * Parses the user's input into the corresponding nova.command.Command object.
     *
     * @param input The user's input string.
     * @return The nova.command.Command object representing the user's command.
     * @throws NovaException If the input is invalid or the command is not recognized.
     */
    public static Command parse(String input) throws NovaException {
        String[] words = input.trim().split(" ", 2);
        String commandWord = words[0].toLowerCase();

        switch (commandWord) {
            case "todo":
                if (words.length < 2 || words[1].trim().isEmpty()) {
                    throw new NovaException("The description of a todo cannot be empty.");
                }
                return new AddTodoCommand(words[1].trim());

            case "deadline":
                if (words.length < 2 || words[1].trim().isEmpty()) {
                    throw new NovaException("The description of a deadline cannot be empty.");
                }
                String[] deadlineParts = words[1].split(" /by ", 2);
                if (deadlineParts.length < 2 || deadlineParts[0].trim().isEmpty() || deadlineParts[1].trim().isEmpty()) {
                    throw new NovaException("Invalid deadline format! Use: 'deadline <description> /by <date>'.");
                }
                return new AddDeadlineCommand(deadlineParts[0].trim(), deadlineParts[1].trim());

            case "event":
                if (words.length < 2 || words[1].trim().isEmpty()) {
                    throw new NovaException("The description of an event cannot be empty.");
                }
                String[] eventParts = words[1].split(" /from | /to ", 3);
                if (eventParts.length < 3 || eventParts[0].trim().isEmpty() || eventParts[1].trim().isEmpty() || eventParts[2].trim().isEmpty()) {
                    throw new NovaException("Invalid event format! Use: 'event <description> /from <start> /to <end>'.");
                }
                return new AddEventCommand(eventParts[0].trim(), eventParts[1].trim(), eventParts[2].trim());

            case "list":
                return new ListCommand();

            case "find":
                if (words.length < 2 || words[1].trim().isEmpty()) {
                    throw new NovaException("Please provide a keyword to search.");
                }
                return new FindCommand(words[1].trim());

            case "mark":
                if (words.length < 2 || words[1].trim().isEmpty()) {
                    throw new NovaException("Please specify the task number to mark as done.");
                }
                int markIndex = parseTaskNumber(words[1].trim());
                return new MarkCommand(markIndex);

            case "unmark":
                if (words.length < 2 || words[1].trim().isEmpty()) {
                    throw new NovaException("Please specify the task number to mark as not done.");
                }
                int unmarkIndex = parseTaskNumber(words[1].trim());
                return new UnmarkCommand(unmarkIndex);

            case "delete":
                if (words.length < 2 || words[1].trim().isEmpty()) {
                    throw new NovaException("Please specify the task number to delete.");
                }
                int deleteIndex = parseTaskNumber(words[1].trim());
                return new DeleteCommand(deleteIndex);

            case "bye":
                return new ExitCommand();

            default:
                throw new NovaException("I'm sorry, but I don't understand that command.");
        }
    }

    /**
     * Parses a task number from the input string.
     *
     * @param input The input string containing the task number.
     * @return The parsed task number as an integer (zero-based index).
     * @throws NovaException If the input is not a valid number.
     */
    private static int parseTaskNumber(String input) throws NovaException {
        try {
            int taskNumber = Integer.parseInt(input) - 1; // Convert to zero-based index
            if (taskNumber < 0) {
                throw new NovaException("nova.task.Task number must be a positive integer.");
            }
            return taskNumber;
        } catch (NumberFormatException e) {
            throw new NovaException("nova.task.Task number must be a valid integer.");
        }
    }
}