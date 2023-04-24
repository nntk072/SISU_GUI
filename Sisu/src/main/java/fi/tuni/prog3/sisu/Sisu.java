package fi.tuni.prog3.sisu;

import javafx.fxml.FXMLLoader;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX Sisu
 */
public class Sisu extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        // Creating a new BorderPane.
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10, 10, 10, 10));

        // Adding HBox to the center of the BorderPane.
        root.setCenter(getCenterHbox());

        // Adding button to the BorderPane and aligning it to the right.
        var quitButton = getQuitButton();
        BorderPane.setMargin(quitButton, new Insets(10, 10, 0, 10));
        root.setBottom(quitButton);
        BorderPane.setAlignment(quitButton, Pos.TOP_RIGHT);

        // import fxml files
        FXMLLoader fxmlLoginLoader = new FXMLLoader(Sisu.class.getResource("login.fxml"));
        FXMLLoader fxmlRegisterLoader = new FXMLLoader(Sisu.class.getResource("sign-up.fxml"));

        // root here was defined above
        Scene authenticatedUserScene = new Scene(root, 800, 500);
        Scene loginScene = new Scene(fxmlLoginLoader.load(), 800, 500);
        Scene registerScene = new Scene(fxmlRegisterLoader.load(), 800, 500);

        LoginController loginController = fxmlLoginLoader.getController();
        loginController.setSceneAfterAuthentication(authenticatedUserScene);
        loginController.setRegistrationScene(registerScene);
        loginController.setStage(stage);

        SignUpController registerController = fxmlRegisterLoader.getController();
        registerController.setSceneAfterAuthentication(authenticatedUserScene);
        registerController.setLoginScene(loginScene);
        registerController.setStage(stage);

        stage.setScene(loginScene);
        stage.setTitle("SisuGUI");
        stage.show();

        stage.sceneProperty().addListener((observable, oldScene, newScene) -> {
            System.out.println("changed scene");
            User loggedinUser = loginController.getAuthenticatedUser();
            User registeredUser = registerController.getAuthenticatedUser();
            if (loggedinUser != null) {
                // do what you want with user info
                System.out.println(loggedinUser);
                VBox userDetails = new VBox();
                userDetails.setPrefWidth(250);
                userDetails.setPadding(new Insets(10, 10, 10, 10));
                userDetails.getChildren().addAll(new Label("Welcome " + loggedinUser.getFirstname()),
                        new Label("email: " + loggedinUser.getEmail()),
                        new Label("Student Number: " + loggedinUser.getStudentNumber()));
                root.setRight(userDetails);
            }

            if (registeredUser != null) {
                // do what you want with user info
                System.out.println(registeredUser);
                VBox userDetails = new VBox();
                userDetails.setPrefWidth(250);
                userDetails.setPadding(new Insets(10, 10, 10, 10));
                userDetails.getChildren().addAll(new Label("Welcome " + registeredUser.getFirstname()),
                        new Label("email: " + registeredUser.getEmail()),
                        new Label("Student Number: " + registeredUser.getStudentNumber()));
                root.setRight(userDetails);
            }
        });

    }

    public static void main(String[] args) {
        launch();
    }

    private HBox getCenterHbox() {
        // Creating an HBox.
        HBox centerHBox = new HBox(10);

        // Adding two VBox to the HBox.
        centerHBox.getChildren().addAll(getLeftVBox(), getRightVBox());

        return centerHBox;
    }

    private VBox getLeftVBox() {
        // Creating a VBox for the left side.
        VBox leftVBox = new VBox();
        leftVBox.setPrefWidth(380);
        leftVBox.setStyle("-fx-background-color: #8fc6fd;");

        leftVBox.getChildren().add(new Label("Left Panel"));

        return leftVBox;
    }

    private VBox getRightVBox() {
        // Creating a VBox for the right side.
        VBox rightVBox = new VBox();
        rightVBox.setPrefWidth(380);
        rightVBox.setStyle("-fx-background-color: #b1c2d4;");

        rightVBox.getChildren().add(new Label("Right Panel"));

        return rightVBox;
    }

    private Button getQuitButton() {
        // Creating a button.
        Button button = new Button("Quit");

        // Adding an event to the button to terminate the application.
        button.setOnAction((ActionEvent event) -> {
            Platform.exit();
        });

        return button;
    }
}