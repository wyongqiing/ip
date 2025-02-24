package nova.ui;

import java.util.List;
import java.util.Scanner;

import nova.task.Task;
import nova.task.TaskList;

public class UiNova {
    private Scanner sc;

    public UiNova() {
        sc = new Scanner(System.in);
    }

    public void printWelcomeMessage() {
        System.out.println("Welcome to NovaBot!");
        System.out.println("How can I assist you? Type 'help' to see available commands.");
    }

    public void printHelpMessage() {
        printHorizontalLine();
        System.out.println("----------------------");
        System.out.println("HELP       - Displays this help message.");
        System.out.println("FIND       - Find tasks by keyword. Usage: find <keyword>");
        System.out.println("LIST       - Displays all tasks.");
        System.out.println("MARK       - Marks a task as completed. Usage: mark <task number>");
        System.out.println("UNMARK     - Unmarks a completed task. Usage: unmark <task number>");
        System.out.println("TODO       - Adds a todo. Usage: todo <task>");
        System.out.println("DEADLINE   - Adds a deadline. Usage: deadline <task> /by <date>");
        System.out.println("EVENT      - Adds an event. Usage: event <task> /from <start> /to <end>");
        System.out.println("DELETE     - Deletes a task. Usage: delete <task number>");
        System.out.println("EXIT       - Exits the program.");
        System.out.println("BYE        - Ends the program.");
        printHorizontalLine();
    }

    public void printFarewell() {
        printHorizontalLine();
        System.out.println(" Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public void printTaskDeletedMessage(Task task, int remainingTasks) {
        printHorizontalLine();
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + remainingTasks + (remainingTasks == 1 ? " task" : " tasks") + " in the list.");
        printHorizontalLine();
    }


    public void printErrorMessage(String message) {
        printHorizontalLine();
        System.out.println(" OOPS!!! " + message);
        printHorizontalLine();
    }

    public void printTaskAddedMessage(Task task, int taskCount) {
        printHorizontalLine();
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + taskCount + " tasks in the list.");
        printHorizontalLine();
    }

    public void printTaskSearchResults(List<Task> matchingTasks) {
        printHorizontalLine();
        if (matchingTasks.isEmpty()) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println((i + 1) + "." + matchingTasks.get(i));
            }
        }
        printHorizontalLine();
    }

    public void printMessage(String message) {
        printHorizontalLine();
        System.out.println(message);
        printHorizontalLine();
    }

    public void printTaskUpdatedMessage(String message, Task task) {
        printHorizontalLine();
        System.out.println(" " + message);
        System.out.println("   " + task);
        printHorizontalLine();
    }

    public void showTaskMarked(Task task) {
        printHorizontalLine();
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + task);
        printHorizontalLine();
    }

    public void showTaskUnmarked(Task task) {
        printHorizontalLine();
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + task);
        printHorizontalLine();
    }

    public void printTaskList(TaskList taskList) {
        printHorizontalLine();
        if (taskList.isEmpty()) {
            System.out.println(" No tasks found!");
        } else {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < taskList.getSize(); i++) {
                System.out.println(" " + (i + 1) + "." + taskList.getTask(i));
            }
        }
        printHorizontalLine();
    }
}

