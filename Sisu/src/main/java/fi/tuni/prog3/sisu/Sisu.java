package fi.tuni.prog3.sisu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import fi.tuni.prog3.sisu.Module;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
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

    // Variables for status of program
    TabPane tabPane = mainWindow();

    //Variables for displaying structure of studies
    private static TreeMap<String, String> degreeProgramme_list = new TreeMap<>();

    // Variables for displaying the structure of degrees
    private DegreeModule degreeModule = null;

    // Variables for getting the condition of each module/course
    private TreeMap<String, Integer> selectedCourses = new TreeMap<>();

    @Override
    public void start(Stage stage) throws IOException {

        degreeProgramme_list = DegreeProgramme.getDegreeProgramme_list();
        tabPane = mainWindow();

        // Import FXML File
        FXMLLoader fxmlLoginLoader = new FXMLLoader(Sisu.class.getResource("login.fxml"));
        FXMLLoader fxmlRegisterLoader = new FXMLLoader(Sisu.class.getResource("sign-up.fxml"));

        // Define all the Scenes
        Scene authenticatedUserScene = new Scene(tabPane, 800, 500);
        Scene loginScene = new Scene(fxmlLoginLoader.load(), 600, 500);
        Scene registerScene = new Scene(fxmlRegisterLoader.load(), 607, 562);

        // Get the controller from the Login File
        LoginController loginController = fxmlLoginLoader.getController();
        loginController.setSceneAfterAuthentication(authenticatedUserScene);
        loginController.setRegistrationScene(registerScene);
        loginController.setStage(stage);

        // Get the controller from the Register File
        SignUpController registerController = fxmlRegisterLoader.getController();
        registerController.setSceneAfterAuthentication(authenticatedUserScene);
        registerController.setLoginScene(loginScene);
        registerController.setStage(stage);

        // Set the scene to the stage and show the stage to the user.
        stage.setScene(loginScene);
        stage.setTitle("SisuGUI");
        stage.show();

        // Add listener to the scene property of the stage
        stage.sceneProperty().addListener((observable, oldScene, newScene) -> {
            //System.out.println("changed scene");
            User loggedinUser = loginController.getAuthenticatedUser();
            User registeredUser = registerController.getAuthenticatedUser();

            if (loggedinUser != null || registeredUser != null) {
                User user = loggedinUser != null ? loggedinUser : registeredUser;
                //System.out.println(user);
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
        centerHBox.setId("centerHBox");

        return centerHBox;
    }

    private VBox getLeftVBox() {
        // Creating a VBox for the left side.
        VBox leftVBox = new VBox();
        leftVBox.setPrefWidth(380);
        //leftVBox.setStyle("-fx-background-color: #8fc6fd;");
        //leftVBox.getChildren().add(new Label("Left Panel"));
        leftVBox.setId("leftVBox");

        // Create treeView for listing all the DegreeModule, Module, Courses in the logical way
        TreeView<String> treeView = new TreeView<>();
        // Set id for the treeview
        treeView.setId("treeView");
        leftVBox.getChildren().add(treeView);

        return leftVBox;
    }

    private VBox getRightVBox() {
        // Creating a VBox for the right side.
        VBox rightVBox = new VBox();
        rightVBox.setPrefWidth(380);
        //rightVBox.setStyle("-fx-background-color: #b1c2d4;");
        rightVBox.setId("rightVBox");
        //Add the label to represent the number of credits, which is the total value of the selectedCourses
        Label creditsLabel = new Label("Credits: 0");
        creditsLabel.setId("creditsLabel");
        rightVBox.getChildren().add(creditsLabel);

        // Create a ListView for listing all the courses and their checkboxes in the logical way
        ListView<HBox> listView = new ListView<>();
        listView.setId("listView");
        listView.setPrefHeight(400);
        listView.setPrefWidth(380);

        rightVBox.getChildren().add(listView);

        return rightVBox;
    }

    /**
     * Creating a quit button to end the program
     *
     * @return quit button
     */
    private Button getQuitButton() {
        // Creating a button.
        Button button = new Button("Quit");

        // Adding an event to the button to terminate the application.
        button.setOnAction((ActionEvent event) -> {
            Platform.exit();
        });

        return button;
    }

    /**
     *
     * @return tabPane
     *
     * Create the mainWindow with a tabPane and 2 tabs. Create the tab for
     * student info and add it to the tabPane. Create the tab for structure and
     * add it to the tabPane.
     */
    private TabPane mainWindow() {
        // Create a tabPane
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
        tab_student_info(tabPane);
        tab_structure(tabPane);
        return tabPane;
    }

    /**
     *
     * @param tabPane
     *
     * Create the tab for student info and add it to the tabPane. Create a
     * gridPane for the tab and make the gridPane in the middle of the tab.
     * Create labels for the gridPane.
     */
    private void tab_student_info(TabPane tabPane) {
        //Create tab for student info
        Tab tab = new Tab();
        tab.setText("Student Info");
        tab.setClosable(false);
        tabPane.getTabs().add(tab);

        //Create a gridPane for the tab and make the gridPane in the middle of the tab
        GridPane gridPane = new GridPane();
        // Set id for grid
        gridPane.setId("infoGrid");
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Set the size of the gridPane to be large suitable
        gridPane.setPrefSize(800, 500);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        //Create labels for the gridPane, print the Student information.
        Label firstNameLabel = new Label("First Name: ");
        GridPane.setConstraints(firstNameLabel, 0, 0);
        Label lastNameLabel = new Label("Last Name: ");
        GridPane.setConstraints(lastNameLabel, 0, 1);
        Label emailLabel = new Label("Email: ");
        GridPane.setConstraints(emailLabel, 0, 2);
        Label studentNumberLabel = new Label("Student Number: ");
        GridPane.setConstraints(studentNumberLabel, 0, 3);
        Label startDateLabel = new Label("Start Date: ");
        GridPane.setConstraints(startDateLabel, 0, 4);
        firstNameLabel.setId("firstNameLabel");
        lastNameLabel.setId("lastNameLabel");
        emailLabel.setId("emailLabel");
        studentNumberLabel.setId("studentNumberLabel");
        startDateLabel.setId("startingDateLabel");

        // Set the font size of the label to be as large as suitable
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

        // Labels for choosing the degree
        Label degreeProgrammeLabel = new Label("Choosing your degree programme:");
        degreeProgrammeLabel.setFont(new Font(20));
        GridPane.setConstraints(degreeProgrammeLabel, 1, 0);

        // Add the choice box for the degree selection
        ComboBox<String> degreeProgrammeComboBox = new ComboBox<>();
        GridPane.setConstraints(degreeProgrammeComboBox, 1, 1);
        // Add the items to the choice box
        degreeProgrammeComboBox.getItems().addAll(degreeProgramme_list.keySet());
        degreeProgrammeComboBox.setId("degreeProgrammeComboBox");
        degreeProgrammeComboBox.setMaxWidth(300);

        // Labels for choosing the module
        Label moduleLabel = new Label("Choosing your module/track:");
        moduleLabel.setFont(new Font(20));
        GridPane.setConstraints(moduleLabel, 1, 2);

        // Add ComboBox for module/track selection
        ComboBox<String> moduleComboBox = new ComboBox<>();
        moduleComboBox.setMaxWidth(300);
        //moduleComboBox.setDisable(true);
        moduleComboBox.setId("moduleComboBox");
        GridPane.setConstraints(moduleComboBox, 1, 3);

        // Add the items to the choice box
        degreeProgrammeComboBox.setOnAction((event) -> {
            // Clear the moduleComboBox
            moduleComboBox.getItems().clear();
            moduleComboBox.setValue(null);
            // Find the group_id of the degree
            String group_id = degreeProgramme_list.get(degreeProgrammeComboBox.getValue());
            try {
                int credits = DegreeProgramme.getCredits(group_id);
                degreeModule = new DegreeModule(degreeProgrammeComboBox.getValue(), group_id, credits);
                degreeModule.readAllDegree();
                //ArrayList<Module> track = degreeModule.getModules();
                // for (Module module : track) {
                //     moduleComboBox.getItems().add(module.getModuleName());
                //}
                setup(degreeModule);
            } catch (IOException ex) {
                Logger.getLogger(Sisu.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        moduleComboBox.setOnAction((ActionEvent event) -> {
        }
        );

        gridPane.getChildren()
                .addAll(firstNameLabel, lastNameLabel, emailLabel, studentNumberLabel, startDateLabel, quitButton, degreeProgrammeLabel, degreeProgrammeComboBox, moduleLabel, moduleComboBox);
        //grid.getChildren().addAll(firstNameLabel, lastNameLabel, emailLabel, studentNumberLabel, startDateLabel, degreeProgrammeLabel, degreeProgrammeComboBox);
        tab.setContent(gridPane);
    }

    /**
     *
     * @param tabPane
     *
     * Create tab for student info
     */
    private void tab_structure(TabPane tabPane) {
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

    /**
     * Functions for setting up the contents of the all the modules and courses
     *
     * @param module_id
     * @param module_id_name
     * @param group_id
     * @param group_id_name
     * @param credits
     * @param degreeModule
     *
     */
    void setup(DegreeModule degreeModule) {
        //TreeItem<String> rootOfDegree = new TreeItem<>(group_id_name + " (" + credits + " ECTS)");
        TreeItem<String> rootOfDegree = new TreeItem<>(degreeModule.getName());
        TreeView<String> treeView = (TreeView<String>) tabPane.lookup("#treeView");
        ListView<HBox> listView = (ListView<HBox>) tabPane.lookup("#listView");
        // Clear listView
        listView.getItems().clear();
        treeView.setRoot(rootOfDegree);
        rootOfDegree.setExpanded(true);

        // Get the modules of the degreeModules
        ArrayList<Module> tree_item_module = degreeModule.getModules();
        // Set TreeItem for each module and course with the string as "Name xx credits" if it is a course, Name if it is a module
        var rootNode = new TreeItem<>(degreeModule.getName());
        // temp_List for checking if label exists
        ArrayList<String> temp_List = new ArrayList<>();
        for (Module treeItem : tree_item_module) {
            TreeItem<String> moduleItem = new TreeItem<>(treeItem.getModuleName());
            treeItem.getStudyModules().forEach(studyModuleItem -> {
                for (Course courseItem : studyModuleItem.getCourses()) {
                    TreeItem<String> course = new TreeItem<>(courseItem.getCourseName() + " (" + courseItem.getCredits() + " ECTS)");
                    moduleItem.getChildren().add(course);
                    HBox hbox = new HBox();

                    Label label = new Label(courseItem.getCourseName() + " (" + courseItem.getCredits() + " ECTS)");
                    label.setPadding(new Insets(0, 10, 0, 0));
                    // Checking if label exists in temp_List
                    if (temp_List.contains(courseItem.getCourseName())) {
                        continue;
                    } else {
                        temp_List.add(courseItem.getCourseName());
                    }
                    /// Check courseItem area already in selectedCourses. If yes, return a checkBox with ticked already
                    CheckBox checkBox = new CheckBox();
                    checkBox.setPadding(new Insets(0, 10, 0, 0));
                    // Check if courseItem name is in selectedCourses
                    if (selectedCourses.containsKey(courseItem.getCourseName())) {
                        checkBox.setSelected(true);
                    }

                    // Add the event handler for the checkBox
                    checkBox.setOnAction((ActionEvent event) -> {
                        if (checkBox.isSelected()) {
                            selectedCourses.put(courseItem.getCourseName(), courseItem.getCredits());
                            Label creditsLabel = (Label) tabPane.lookup("#creditsLabel");
                            creditsLabel.setText("Credits: " + selectedCourses.values().stream().mapToInt(Integer::intValue).sum());
                        } else {
                            selectedCourses.remove(courseItem.getCourseName());
                            Label creditsLabel = (Label) tabPane.lookup("#creditsLabel");
                            creditsLabel.setText("Credits: " + selectedCourses.values().stream().mapToInt(Integer::intValue).sum());
                        }
                    });

                    hbox.getChildren().addAll(checkBox, label);
                    listView.getItems().add(hbox);
                }
            });
            rootNode.getChildren().add(moduleItem);
        }
        treeView.setRoot(rootNode);
    }
}
