import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public void handleCommand(String command) {
        if (command.equalsIgnoreCase("list")) {
            printTaskList();
        } else if (command.startsWith("todo ")) {
            addTodo(command.substring(5));
        } else if (command.startsWith("deadline ")) {
            String[] parts = command.substring(9).split(" /by ");
            addDeadline(parts[0], parts[1]);
        } else if (command.startsWith("event ")) {
            String[] parts = command.substring(6).split(" /from | /to ");
            addEvent(parts[0], parts[1], parts[2]);
        } else if (command.startsWith("mark ")) {
            int taskIndex = Integer.parseInt(command.substring(5)) - 1;
            markTaskAsDone(taskIndex);
        } else if (command.startsWith("unmark ")) {
            int taskIndex = Integer.parseInt(command.substring(7)) - 1;
            markTaskAsNotDone(taskIndex);
        } else {
            printErrorMessage();
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

    private void addTodo(String description) {
        Task task = new Todo(description);
        tasks.add(task);
        printTaskAddedMessage(task);
    }

    private void addDeadline(String description, String by) {
        Task task = new Deadline(description, by);
        tasks.add(task);
        printTaskAddedMessage(task);
    }

    private void addEvent(String description, String from, String to) {
        Task task = new Event(description, from, to);
        tasks.add(task);
        printTaskAddedMessage(task);
    }

    private void markTaskAsDone(int index) {
        if (isValidIndex(index)) {
            Task task = tasks.get(index);
            task.markAsDone();
            printTaskUpdatedMessage("Nice! I've marked this task as done:", task);
        }
    }

    private void markTaskAsNotDone(int index) {
        if (isValidIndex(index)) {
            Task task = tasks.get(index);
            task.markAsNotDone();
            printTaskUpdatedMessage("OK, I've marked this task as not done yet:", task);
        }
    }

    private boolean isValidIndex(int index) {
        if (index >= 0 && index < tasks.size()) {
            return true;
        } else {
            printErrorMessage();
            return false;
        }
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

    private void printErrorMessage() {
        printHorizontalLine();
        System.out.println(" Sorry, I didn't understand that command.");
        printHorizontalLine();
    }

    private void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }
}

