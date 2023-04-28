/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package fi.tuni.prog3.sisu;

import com.google.gson.JsonObject;
import java.util.TreeMap;
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
public class DegreeProgrammeTest {
    
    public DegreeProgrammeTest() {
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
     * Test of getDegreeProgramme_list method, of class DegreeProgramme.
     */
    @Test
    public void testGetDegreeProgramme_list() throws Exception {
        System.out.println("getDegreeProgramme_list");
        TreeMap<String, String> result = DegreeProgramme.getDegreeProgramme_list();
        String key = "Akuuttilääketieteen erikoislääkärikoulutus (55/2020)";
        String value = "otm-87fb9507-a6dd-41aa-b924-2f15eca3b7ae";
        TreeMap<String, String> expResult = new TreeMap<>();
        expResult.put(key, value);

        // Assert the first key and value of the result andexpResult
        assertEquals(expResult.firstKey(), result.firstKey());
    }

    /**
     * Test of getCredits method, of class DegreeProgramme.
     */
    @Test
    public void testGetCreditsandaddModuleObj() throws Exception {
        System.out.println("getCredits");
        int expResult = 180;
        int result = DegreeProgramme.getCredits("otm-4d4c4575-a5ae-427e-a860-2f168ad4e8ba");
        assertEquals(expResult, result);
    }

    
}
