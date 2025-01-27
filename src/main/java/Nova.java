import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Nova {

    private static List<Task> tasks = new ArrayList<>();
    public static void main(String[] args) {

        // Horizontal lines for aesthetics
        printHorizontalLine();

        // Greeting message
        System.out.println(" Hello! I'm Nova");
        System.out.println(" What can I do for you?");
        printHorizontalLine();

        Scanner sc = new Scanner(System.in);
        String userInput;

        // Continue until the user types "bye"
        while (true) {
            userInput = sc.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                printHorizontalLine();
                System.out.println(" Bye. Hope to see you again soon!");
                printHorizontalLine();
                break;
            }else if (userInput.equalsIgnoreCase("list")) {
                // Display all stored tasks
                printHorizontalLine();
                if (tasks.isEmpty()) {
                    System.out.println(" No tasks found!");
                } else {
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(" " + (i + 1) + ". " + tasks.get(i));
                    }
                }
                printHorizontalLine();
            } else {
                printHorizontalLine();
                System.out.println(" " + userInput);
                printHorizontalLine();
            }
        }
        sc.close();
    }

    /**
     * Prints a horizontal line for visual separation
     */
    private static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

}
