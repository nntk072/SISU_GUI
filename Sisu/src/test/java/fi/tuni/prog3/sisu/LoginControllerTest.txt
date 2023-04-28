/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package fi.tuni.prog3.sisu;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author xblong
 */
public class LoginControllerTest {
    
    public LoginControllerTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of onNavigateToRegisterPage method, of class LoginController.
     */
    @Test
    public void testOnNavigateToRegisterPage() {
        System.out.println("onNavigateToRegisterPage");
        ActionEvent event = null;
        LoginController instance = new LoginController();
        instance.onNavigateToRegisterPage(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setScene method, of class LoginController.
     */
    @Test
    public void testSetScene() {
        System.out.println("setScene");
        Scene scene = null;
        LoginController instance = new LoginController();
        instance.setScene(scene);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAuthenticatedUser method, of class LoginController.
     */
    @Test
    public void testGetAuthenticatedUser() {
        System.out.println("getAuthenticatedUser");
        LoginController instance = new LoginController();
        User expResult = null;
        User result = instance.getAuthenticatedUser();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRegistrationScene method, of class LoginController.
     */
    @Test
    public void testSetRegistrationScene() {
        System.out.println("setRegistrationScene");
        Scene registrationScene = null;
        LoginController instance = new LoginController();
        instance.setRegistrationScene(registrationScene);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSceneAfterAuthentication method, of class LoginController.
     */
    @Test
    public void testSetSceneAfterAuthentication() {
        System.out.println("setSceneAfterAuthentication");
        Scene sceneAfterAuthentication = null;
        LoginController instance = new LoginController();
        instance.setSceneAfterAuthentication(sceneAfterAuthentication);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStage method, of class LoginController.
     */
    @Test
    public void testSetStage() {
        System.out.println("setStage");
        Stage stage = null;
        LoginController instance = new LoginController();
        instance.setStage(stage);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of onLoginSubmit method, of class LoginController.
     */
    @Test
    public void testOnLoginSubmit() {
        System.out.println("onLoginSubmit");
        ActionEvent event = null;
        LoginController instance = new LoginController();
        instance.onLoginSubmit(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
