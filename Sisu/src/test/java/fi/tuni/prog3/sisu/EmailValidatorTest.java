/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package fi.tuni.prog3.sisu;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author xblong
 */
public class EmailValidatorTest {
    
    public EmailValidatorTest() {
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
     * Test of isValidEmail method, of class EmailValidator.
     * john.doe@example.com
     */
    @Test
    public void testIsValidEmail() {
        System.out.println("isValidEmail");
        String email = "john.doe@example.com";
        boolean expResult = true;
        boolean result = EmailValidator.isValidEmail(email);
    }

    /**
     * Test of isValidEmail method, of class EmailValidator.
     * ""
     */
    @Test
    public void testIsValidEmail2() {
        System.out.println("isValidEmail");
        String email = "";
        boolean expResult = false;
        boolean result = EmailValidator.isValidEmail(email);
    }

    /**
     * Test of isValidEmail method, of class EmailValidator.
     * "john.doe"
     */
    @Test
    public void testIsValidEmail3() {
        System.out.println("isValidEmail");
        String email = "john.doe";
        boolean expResult = false;
        boolean result = EmailValidator.isValidEmail(email);
    }


    /**
     * Test of isValidEmail method, of class EmailValidator.
     * "aaaa" Less than for letters
     */
    @Test
    public void testIsValidEmail4() {
        System.out.println("isValidEmail");
        String email = "aaaa";
        boolean expResult = false;
        boolean result = EmailValidator.isValidEmail(email);
    }

    
}
