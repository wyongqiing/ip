import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
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
        printTaskAddedMessage(task);
    }

    private void addDeadline(String command) {
        String[] parts = command.substring(9).split(" /by ");
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new NovaException("Invalid deadline format! Use: 'deadline <description> /by <date>'");
        }
        Task task = new Deadline(parts[0].trim(), parts[1].trim());
        tasks.add(task);
        printTaskAddedMessage(task);
    }

    private void addEvent(String command) {
        String[] parts = command.substring(6).split(" /from | /to ");
        if (parts.length < 3 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
            throw new NovaException("Invalid event format! Use: 'event <description> /from <start> /to <end>'");
        }
        Task task = new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
        tasks.add(task);
        printTaskAddedMessage(task);
    }

    private void markTaskAsDone(String command) {
        int index = parseTaskIndex(command, "mark");
        tasks.get(index).markAsDone();
        printTaskUpdatedMessage("Nice! I've marked this task as done:", tasks.get(index));
    }

    private void markTaskAsNotDone(String command) {
        int index = parseTaskIndex(command, "unmark");
        tasks.get(index).markAsNotDone();
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

//    private boolean isValidIndex(int index) {
//        if (index >= 0 && index < tasks.size()) {
//            return true;
//        } else {
//            printErrorMessage();
//            return false;
//        }
//    }

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

