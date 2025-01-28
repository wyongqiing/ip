import java.util.Scanner;

public class Nova {

    private TaskManager taskManager;

    public Nova() {
        taskManager = new TaskManager();
    }

    public void start() {
        Scanner sc = new Scanner(System.in);

        // Greeting message
        taskManager.printGreeting();

        while (true) {
            String userInput = sc.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                taskManager.printFarewell();
                break;
            }
            // Delegate command handling to the TaskManager
            taskManager.handleCommand(userInput);
        }

        sc.close();
    }

    public static void main(String[] args) {
        Nova nova = new Nova();
        nova.start();
    }

}
