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
public class SignUpControllerTest {
    
    public SignUpControllerTest() {
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
     * Test of getAuthenticatedUser method, of class SignUpController.
     */
    @Test
    public void testGetAuthenticatedUser() {
        System.out.println("getAuthenticatedUser");
        SignUpController instance = new SignUpController();
        User expResult = null;
        User result = instance.getAuthenticatedUser();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setScene method, of class SignUpController.
     */
    @Test
    public void testSetScene() {
        System.out.println("setScene");
        Scene scene = null;
        SignUpController instance = new SignUpController();
        instance.setScene(scene);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSceneAfterAuthentication method, of class SignUpController.
     */
    @Test
    public void testSetSceneAfterAuthentication() {
        System.out.println("setSceneAfterAuthentication");
        Scene sceneAfterAuthentication = null;
        SignUpController instance = new SignUpController();
        instance.setSceneAfterAuthentication(sceneAfterAuthentication);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLoginScene method, of class SignUpController.
     */
    @Test
    public void testSetLoginScene() {
        System.out.println("setLoginScene");
        Scene loginScene = null;
        SignUpController instance = new SignUpController();
        instance.setLoginScene(loginScene);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStage method, of class SignUpController.
     */
    @Test
    public void testSetStage() {
        System.out.println("setStage");
        Stage stage = null;
        SignUpController instance = new SignUpController();
        instance.setStage(stage);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of onNavigateToLogin method, of class SignUpController.
     */
    @Test
    public void testOnNavigateToLogin() {
        System.out.println("onNavigateToLogin");
        ActionEvent event = null;
        SignUpController instance = new SignUpController();
        instance.onNavigateToLogin(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of onRegisterUser method, of class SignUpController.
     */
    @Test
    public void testOnRegisterUser() throws Exception {
        System.out.println("onRegisterUser");
        ActionEvent event = null;
        SignUpController instance = new SignUpController();
        instance.onRegisterUser(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
