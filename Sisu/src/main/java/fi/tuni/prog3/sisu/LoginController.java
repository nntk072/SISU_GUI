package fi.tuni.prog3.sisu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;

public class LoginController {
    public User authenticatedUser;

    private Scene scene;
    private Scene sceneAfterAuthentication;

    private Scene registrationScene;
    private Stage stage;

    @FXML
    private PasswordField loginPassword;

    @FXML
    private Button loginSubmitButton;

    @FXML
    private TextField loginUserName;

    @FXML
    void onNavigateToRegisterPage(ActionEvent event) {
        // access the scene using the member variable
        if (registrationScene != null) {
          stage.setScene(registrationScene);
        }
    }
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public  User getAuthenticatedUser(){
        return authenticatedUser;
    }
    public void setRegistrationScene(Scene registrationScene) {
        this.registrationScene = registrationScene;
    }


    public void setSceneAfterAuthentication(Scene sceneAfterAuthentication){
        this.sceneAfterAuthentication = sceneAfterAuthentication;
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    @FXML
    void onLoginSubmit(ActionEvent event) {
      String email = loginUserName.getText();
      String password = loginPassword.getText();

      if(email.equals("") || password.equals("")){
          MessageDisplayer.displayMessage("Username or Password cannot be empty");
      }else{
          try {
              List<User> allUsers = UserDeserializer.loadUsers();

              User currentUser = UserDeserializer.getUserByEmailAndPassword(allUsers, email, password);
             if(currentUser == null){
                 MessageDisplayer.displayMessage("Incorrect email or password, please try again");
             }else{
                 this.authenticatedUser = currentUser;
                 stage.setScene(sceneAfterAuthentication);
             }
          } catch (IOException e) {
            MessageDisplayer.displayMessage("Something went wrong, please contact support");
            System.err.println("Error saving user: " + e.getMessage());
            e.printStackTrace();
        }


      }


    }

}
