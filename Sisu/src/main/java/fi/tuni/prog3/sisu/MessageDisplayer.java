package fi.tuni.prog3.sisu;

import javafx.scene.control.Alert;

public class MessageDisplayer {
    public static void displayMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
