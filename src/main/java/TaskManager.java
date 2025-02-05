import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private final List<Task> tasks;
    private final String folderPath = "./data";
    private final String filePath = folderPath + "/nova.txt";

    public TaskManager() {
        tasks = loadTasks();
    }

    public void handleCommand(String command) {
        try {
            if (command.equalsIgnoreCase("list")) {
                printTaskList();
            } else if (command.startsWith("todo ")) {
                addTodo(command);
            } else if (command.startsWith("deadline ")) {
                addDeadline(command);
            } else if (command.startsWith("event ")) {
                addEvent(command);
            } else if (command.startsWith("mark ")) {
                markTaskAsDone(command);
            } else if (command.startsWith("unmark ")) {
                markTaskAsNotDone(command);
            } else if (command.startsWith("delete ")) {
                deleteTask(command);
            } else {
                throw new NovaException("I'm sorry, but I don't understand that command.");
            }
        } catch (NovaException e) {
            printErrorMessage(e.getMessage());
        }
    }

    public void printGreeting() {
        printHorizontalLine();
        System.out.println(" Hello! I'm Nova");
        System.out.println(" What can I do for you?");
        printHorizontalLine();
    }

    public void printFarewell() {
        printHorizontalLine();
        System.out.println(" Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    private void printTaskList() {
        printHorizontalLine();
        if (tasks.isEmpty()) {
            System.out.println(" No tasks found!");
        } else {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + "." + tasks.get(i));
            }
        }
        printHorizontalLine();
    }

    private void addTodo(String command) {
        String description = command.substring(5).trim();
        if (description.isEmpty()) {
            throw new NovaException("The description of a todo cannot be empty.");
        }
        Task task = new Todo(description);
        tasks.add(task);
        saveTasks();
        printTaskAddedMessage(task);
    }

    private void addDeadline(String command) {
        String[] parts = command.substring(9).split(" /by ");
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new NovaException("Invalid deadline format! Use: 'deadline <description> /by <date>'");
        }

        String description = parts[0].trim();
        String byStr = parts[1].trim();

        try {
            // Parse the 'by' string into a LocalDateTime
            LocalDateTime by = DateTimeParser.parse(byStr);
            Task task = new Deadline(description, by);
            tasks.add(task);
            saveTasks();
            printTaskAddedMessage(task);
        } catch (DateTimeParseException e) {
            throw new NovaException("Invalid date format! Please use 'M/d/yyyy HHmm'.");
        }
    }

    private void addEvent(String command) {
        String[] parts = command.substring(6).split(" /from | /to ");
        if (parts.length < 3 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
            throw new NovaException("Invalid event format! Use: 'event <description> /from <start> /to <end>'");
        }
        Task task = new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
        tasks.add(task);
        saveTasks();
        printTaskAddedMessage(task);
    }

    private void markTaskAsDone(String command) {
        int index = parseTaskIndex(command, "mark");
        tasks.get(index).markAsDone();
        saveTasks();
        printTaskUpdatedMessage("Nice! I've marked this task as done:", tasks.get(index));
    }

    private void markTaskAsNotDone(String command) {
        int index = parseTaskIndex(command, "unmark");
        tasks.get(index).markAsNotDone();
        saveTasks();
        printTaskUpdatedMessage("OK, I've marked this task as not done yet:", tasks.get(index));
    }

    private int parseTaskIndex(String command, String action) {
        try {
            int index = Integer.parseInt(command.substring(action.length()).trim()) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new NovaException("Task number is invalid or out of range.");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new NovaException("Please provide a valid task number.");
        }
    }

    private void deleteTask(String command) {
        try {
            // Extract and validate the task index
            int index = parseTaskIndex(command, "delete");
            // Remove the task and store it for confirmation
            Task removedTask = tasks.remove(index);
            saveTasks();
            // Print confirmation message
            printHorizontalLine();
            System.out.println(" Noted. I've removed this task:");
            System.out.println("   " + removedTask);
            System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
            printHorizontalLine();
        } catch (NovaException e) {
            printErrorMessage(e.getMessage());
        }
    }

    private void saveTasks() {
        try {
            File folder = new File(folderPath);
            if (!folder.exists() && !folder.mkdirs()) {
                System.out.println("Failed to create directory: " + folderPath);
            }

            FileWriter writer = new FileWriter(filePath);
            for (Task task : tasks) {
                writer.write(task.encode() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    private List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("No existing data file found. Starting with an empty task list.");
            return tasks;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue; // Skip empty lines
                }

                try {
                    Task task = Task.decode(line);
                    tasks.add(task);
                } catch (IllegalArgumentException e) {
                    System.out.println("Skipping invalid task: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    private void printTaskAddedMessage(Task task) {
        printHorizontalLine();
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        printHorizontalLine();
    }

    private void printTaskUpdatedMessage(String message, Task task) {
        printHorizontalLine();
        System.out.println(" " + message);
        System.out.println("   " + task);
        printHorizontalLine();
    }

    private void printErrorMessage(String message) {
        printHorizontalLine();
        System.out.println(" OOPS!!! " + message);
        printHorizontalLine();
    }

    private void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }
}

