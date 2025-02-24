package nova.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import nova.ui.Nova;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Nova nova;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/pingu.jpeg"));
    private Image novaImage = new Image(this.getClass().getResourceAsStream("/images/potato.jpeg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setNova(Nova nova) {
        this.nova = nova;
        showWelcomeMessage();
    }
    private void showWelcomeMessage() {
        String welcomeMessage = "Hello! I'm Nova 🤖\n" + "How can I assist you today?\n" + "Type 'help' to see available commands.";
        dialogContainer.getChildren().add(DialogBox.getNovaDialog(welcomeMessage, novaImage));
    }


    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = nova.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getNovaDialog(response, novaImage)
        );
        userInput.clear();

        if (input.equalsIgnoreCase("exit")) {
            System.exit(0);
        }

    }

}
