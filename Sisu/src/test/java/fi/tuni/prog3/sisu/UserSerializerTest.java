/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package fi.tuni.prog3.sisu;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.List;

/**
 *
 * @author xblong
 */
public class UserSerializerTest {
    private User testUser;
    private static final String USER_FILE = "usersStore.json";

    public UserSerializerTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        String firstname = "Test";
        String lastname = "Test";
        String email = "haha@haha.tuni.fi";

        int year = 2022;
        String studentNumber = "11111111";
        String startingDate = "27-4-2023";
        String password = "haha";
        testUser = new User(firstname, lastname, email, password, year, studentNumber, startingDate);
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of saveUser method, of class UserSerializer.
     */
    @Test
    public void testSaveUser() throws Exception {
        File file = new File(USER_FILE);
        if (file.exists()) {
            file.delete();
        }

        // Save a user
        UserSerializer.saveUser(testUser);

        // Read the users from the file
        List<User> users = UserSerializer.readUsers();

        // Check that the saved user is in the list
        boolean found = false;
        for (User user : users) {
            if (user.getEmail().equals(testUser.getEmail())) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }

}
