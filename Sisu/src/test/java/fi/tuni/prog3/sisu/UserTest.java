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

/**
 *
 * @author xblong
 */
public class UserTest {

    public UserTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    private User user;

    @BeforeEach
    public void setUp() {
        // Create random variable for suitable with User parameters
        String firstname = "Test";
        String lastname = "Test";
        String email = "haha@haha.tuni.fi";

        int year = 2022;
        String studentNumber = "11111111";
        String startingDate = "27-4-2023";
        String password = "haha";

        // Create new User instance
        user = new User(firstname, lastname, email, password, year, studentNumber, startingDate);
    }

    @Test
    public void testGetters() {
        assertEquals("Test", user.getFirstname());
        assertEquals("Test", user.getLastname());
        assertEquals("haha@haha.tuni.fi", user.getEmail());
        assertEquals(2022, user.getYear());
        assertEquals("11111111", user.getStudentNumber());
        assertEquals("27-4-2023", user.getStartingDate());
        assertEquals("haha", user.getPassword());
    }

    @Test
    public void testSetEmail() {
        user.setEmail("haha@tuni.fi");
        assertEquals("haha@tuni.fi", user.getEmail());
    }

    @Test
    public void testSetPassword() {
        user.setPassword("newpassword");
        assertEquals("newpassword", user.getPassword());
    }

    @Test
    public void testSetYear() {
        user.setYear(2023);
        assertEquals(2023, user.getYear());
    }

    @Test
    public void testSetStudentNumber() {
        user.setStudentNumber("22222222");
        assertEquals("22222222", user.getStudentNumber());
    }

    @Test
    public void testToString() {
        String expected = "User{firstname='Test', lastname='Test', email='haha@haha.tuni.fi', password='haha', year=2022, studentNumber='11111111', startingDate='27-4-2023'}";
        assertEquals(expected, user.toString());
    }

}

