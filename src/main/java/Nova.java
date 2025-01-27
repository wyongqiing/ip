import java.util.Scanner;

public class Nova {
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
