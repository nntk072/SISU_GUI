package fi.tuni.prog3.sisu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.Random;
import javafx.stage.Stage;

import java.io.IOException;

import java.time.LocalDate;

public class SignUpController {
    User authenticatedUser;
    private Scene scene;
    private Scene sceneAfterAuthentication;

    private Scene loginScene;
    private Stage stage;

    @FXML
    private TextField regFirstname;

    @FXML
    private TextField regLastname;

    @FXML
    private TextField regName;

    @FXML
    private PasswordField regPassword;

    @FXML
    private DatePicker regStartDate;

    @FXML
    private Button regSubmitButton;

    public  User getAuthenticatedUser(){
        return authenticatedUser;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
    public void setSceneAfterAuthentication(Scene sceneAfterAuthentication){
        this.sceneAfterAuthentication = sceneAfterAuthentication;
    }
    public void setLoginScene(Scene loginScene) {
        this.loginScene = loginScene;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    void onNavigateToLogin(ActionEvent event) {
        // access the scene using the member variable
        if (loginScene != null) {
           stage.setScene(loginScene);
        }
    }

    @FXML
    void onRegisterUser(ActionEvent event) {
      String firstname = regFirstname.getText();
      String lastname = regLastname.getText();
      String email = regName.getText();
      String password = regPassword.getText();
      LocalDate isoDate = regStartDate.getValue();
        int year = 0;
        if (isoDate != null) {
            year = isoDate.getYear();
        }
      //get current date to compare selected date
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();

        if(firstname.equals("") || lastname.equals("") || email.equals("") || password.equals("") || year == 0){
            MessageDisplayer.displayMessage("All fields are required");
        } else if (password.length() < 5) {
            MessageDisplayer.displayMessage("Password should be more than 4 characters");
        } else if (year < currentYear) {
            MessageDisplayer.displayMessage("Select a recent year");
        } else if (!EmailValidator.isValidEmail(email)) {
            MessageDisplayer.displayMessage("Please enter a valid email");
        } else {
            //save to json file and show next screen with user details
            try {
                Random random = new Random();
                // generate a random number between 10000000 and 99999999
                int randomNum = random.nextInt(90000000) + 10000000;
                String studentNumber = Integer.toString(randomNum);

                UserSerializer.saveUser(new User(firstname,lastname,email,password,year, studentNumber));
                List<User> allUsers = UserDeserializer.loadUsers();
                User currentUser = UserDeserializer.getUserByEmailAndPassword(allUsers, email, password);
                this.authenticatedUser = currentUser;

                stage.setScene(sceneAfterAuthentication);
            } catch (IOException e) {
                MessageDisplayer.displayMessage("Something went wrong, please contact support");
                System.err.println("Error saving user: " + e.getMessage());
                e.printStackTrace();
            }

        }
    }

}

