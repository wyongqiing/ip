package nova.ui;

import nova.task.Task;
import nova.task.TaskList;

import java.util.Scanner;

public class UiNova {
    private Scanner sc;

    public UiNova() {
        sc = new Scanner(System.in);
    }

    public void printGreeting() {
        printHorizontalLine();
        System.out.println(" Hello! I'm nova.ui.Nova");
        System.out.println(" What can I do for you?");
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

    public void printTaskUpdatedMessage(String message, Task task) {
        printHorizontalLine();
        System.out.println(" " + message);
        System.out.println("   " + task);
        printHorizontalLine();
    }

    public void showTaskRemoved(Task task, int taskCount) {
        printHorizontalLine();
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + taskCount + " tasks in the list.");
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