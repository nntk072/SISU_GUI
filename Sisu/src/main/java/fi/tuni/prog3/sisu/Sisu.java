package fi.tuni.prog3.sisu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * JavaFX Sisu
 */
public class Sisu extends Application {

    private static TreeMap<String, String> degreeProgramme_list = new TreeMap<>();
    private static TreeMap<String, String> module_list = new TreeMap<>();
    //private static TreeMap<String, String> module_group_id_list = new TreeMap<>();

    @Override
    public void start(Stage stage) throws IOException {

        degreeProgramme_list = DegreeProgramme.getDegreeProgramme_list();

        //module = StudyModule.getModule();
        // Creating mainwindow.
        TabPane tabPane = mainwindow();

        // import fxml files
        FXMLLoader fxmlLoginLoader = new FXMLLoader(Sisu.class.getResource("login.fxml"));
        FXMLLoader fxmlRegisterLoader = new FXMLLoader(Sisu.class.getResource("sign-up.fxml"));

        // root here was defined above
        Scene authenticatedUserScene = new Scene(tabPane, 800, 500);
        Scene loginScene = new Scene(fxmlLoginLoader.load(), 600, 500);
        Scene registerScene = new Scene(fxmlRegisterLoader.load(), 607, 562);

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

            if (loggedinUser != null || registeredUser != null) {
                // do what you want with user info 
                User user = loggedinUser != null ? loggedinUser : registeredUser;
                System.out.println(user);
                Label firstNameLabel = (Label) tabPane.lookup("#firstNameLabel");
                firstNameLabel.setText(firstNameLabel.getText() + user.getFirstname());
                Label lastNameLabel = (Label) tabPane.lookup("#lastNameLabel");
                lastNameLabel.setText(lastNameLabel.getText() + user.getLastname());
                Label emailLabel = (Label) tabPane.lookup("#emailLabel");
                emailLabel.setText(emailLabel.getText() + user.getEmail());
                Label studentNumberLabel = (Label) tabPane.lookup("#studentNumberLabel");
                studentNumberLabel.setText(studentNumberLabel.getText() + user.getStudentNumber());
                Label startingDateLabel = (Label) tabPane.lookup("#startingDateLabel");
                startingDateLabel.setText(startingDateLabel.getText() + user.getStartingDate());
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

        // Set id
        centerHBox.setId("centerHBox");

        return centerHBox;
    }

    private VBox getLeftVBox() {
        // Creating a VBox for the left side.
        VBox leftVBox = new VBox();
        leftVBox.setPrefWidth(380);
        leftVBox.setStyle("-fx-background-color: #8fc6fd;");
        leftVBox.getChildren().add(new Label("Left Panel"));
        //Set id for leftVBox
        leftVBox.setId("leftVBox");

        return leftVBox;
    }

    private VBox getRightVBox() {
        // Creating a VBox for the right side.
        VBox rightVBox = new VBox();
        rightVBox.setPrefWidth(380);
        rightVBox.setStyle("-fx-background-color: #b1c2d4;");

        rightVBox.getChildren().add(new Label("Right Panel"));
        //Set id for rightVBox
        rightVBox.setId("rightVBox");

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

    private TabPane mainwindow() {
        //Create mainwindow with a tabpane and 2 tabs
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

        tab_student_info(tabPane);
        tab_structure(tabPane);

        return tabPane;

    }

    private void tab_student_info(TabPane tabPane) {
        //Create tab for student info
        Tab tab = new Tab();
        tab.setText("Student Info");
        tab.setClosable(false);
        tabPane.getTabs().add(tab);

        //Create a gridpane for the tab and make the gridpane in the middle of the tab
        GridPane grid = new GridPane();
        // Set id for grid
        grid.setId("infoGrid");
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        // Set the size of the grid to be large suitable
        grid.setPrefSize(800, 500);
        //Set the padding to be large suitable
        grid.setPadding(new Insets(25, 25, 25, 25));
        // Show the grid lines
        //grid.setGridLinesVisible(true);

        //Create labels and textfields for the gridpane
        Label firstNameLabel = new Label("First Name: ");
        GridPane.setConstraints(firstNameLabel, 0, 0);
        // Last name
        Label lastNameLabel = new Label("Last Name: ");
        GridPane.setConstraints(lastNameLabel, 0, 1);
        // Email
        Label emailLabel = new Label("Email: ");
        GridPane.setConstraints(emailLabel, 0, 2);
        // Student number
        Label studentNumberLabel = new Label("Student Number: ");
        GridPane.setConstraints(studentNumberLabel, 0, 3);
        // Start date
        Label startDateLabel = new Label("Start Date: ");
        GridPane.setConstraints(startDateLabel, 0, 4);

        // Set id for all labels above by thy name
        firstNameLabel.setId("firstNameLabel");
        lastNameLabel.setId("lastNameLabel");
        emailLabel.setId("emailLabel");
        studentNumberLabel.setId("studentNumberLabel");
        startDateLabel.setId("startingDateLabel");

        // Set the font size of the label
        firstNameLabel.setFont(new Font(20));
        lastNameLabel.setFont(new Font(20));
        emailLabel.setFont(new Font(20));
        studentNumberLabel.setFont(new Font(20));
        startDateLabel.setFont(new Font(20));

        // Adding button to the GridPane and aligning it to the right.
        var quitButton = getQuitButton();
        GridPane.setMargin(quitButton, new Insets(10, 10, 0, 10));
        GridPane.setConstraints(quitButton, 1, 5);
        GridPane.setHalignment(quitButton, HPos.RIGHT);

        //Create label "Choosing your degree programme:"
        Label degreeProgrammeLabel = new Label("Choosing your degree programme:");
        GridPane.setConstraints(degreeProgrammeLabel, 1, 0);
        // Set the suitable font
        degreeProgrammeLabel.setFont(new Font(20));

        // Add the choice box for the GridPane (the selection is the degree programme listed in the key of degreeprogramme_list)
        ChoiceBox<String> degreeProgrammeChoiceBox = new ChoiceBox<>();
        GridPane.setConstraints(degreeProgrammeChoiceBox, 1, 1);
        // Add the items to the choice box
        degreeProgrammeChoiceBox.getItems().addAll(degreeProgramme_list.keySet());
        // Set id for the degreeprogrammechoicebox
        degreeProgrammeChoiceBox.setId("degreeProgrammeChoiceBox");
        //Make the width of degreeprogrammechoicebox to be long suitable in the grid
        degreeProgrammeChoiceBox.setMaxWidth(300);
        // Add label for module
        Label moduleLabel = new Label("Choosing your module:");
        GridPane.setConstraints(moduleLabel, 1, 2);

        // Set the suitable font
        moduleLabel.setFont(new Font(20));

        // Add choiceBox for module (the selection is the elements of module listed in the module getter name list)
        ChoiceBox<String> moduleChoiceBox = new ChoiceBox<>();
        //Make the width of moduleChoiceBox to be long suitable in the grid
        moduleChoiceBox.setMaxWidth(300);

        //Set disable if degreeProgrammeChoiceBox hasn't been chosen any selection
        moduleChoiceBox.setDisable(true);
        degreeProgrammeChoiceBox.setOnAction((event) -> {
            
            // Clear the moduleChoiceBox
            moduleChoiceBox.getItems().clear();
            // Find the group_id of the chosen from the degreeProgrammeChoiceBox
            String group_id = degreeProgramme_list.get(degreeProgrammeChoiceBox.getValue());
            try {
                // Add the items to the choice box using for function
                module_list = DegreeProgramme.getModule(group_id);
            } catch (IOException ex) {
                Logger.getLogger(Sisu.class.getName()).log(Level.SEVERE, null, ex);
            }
            // Check if module_list is null
            if (module_list != null) {
                // Add the items to the choice box using for function
                moduleChoiceBox.getItems().addAll(module_list.keySet());
                // Set disable to false
                moduleChoiceBox.setDisable(false);
            } else {
                // Set disable to true
                moduleChoiceBox.setDisable(true);
            }
            
            // Print all key of module_list using system.out.println for checking
        });

        GridPane.setConstraints(moduleChoiceBox, 1, 3);
        // Add the items to the choice box using for function

        //Add everything to the gridpane
        grid.getChildren().addAll(firstNameLabel, lastNameLabel, emailLabel, studentNumberLabel, startDateLabel, quitButton, degreeProgrammeLabel, degreeProgrammeChoiceBox, moduleLabel, moduleChoiceBox);
        //grid.getChildren().addAll(firstNameLabel, lastNameLabel, emailLabel, studentNumberLabel, startDateLabel, degreeProgrammeLabel, degreeProgrammeChoiceBox);

        //Add gridpane to the tab
        tab.setContent(grid);

    }

    private void tab_structure(TabPane tabPane) {
        //Create tab for student info
        Tab tab = new Tab();
        tab.setText("Structure of studies");
        tab.setClosable(false);
        tabPane.getTabs().add(tab);

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10, 10, 10, 10));

        // Adding HBox to the center of the BorderPane.
        root.setCenter(getCenterHbox());

        // Adding button to the BorderPane and aligning it to the right.
        var quitButton = getQuitButton();
        BorderPane.setMargin(quitButton, new Insets(10, 10, 0, 10));
        root.setBottom(quitButton);
        BorderPane.setAlignment(quitButton, Pos.TOP_RIGHT);

        tab.setContent(root);
    }

}
