/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package fi.tuni.prog3.sisu;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.gson.JsonObject;

import fi.tuni.prog3.sisu.Module;

/**
 *
 * @author xblong
 */
public class DegreeModuleTest {

    public DegreeModuleTest() {
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
     * Test Getters
     */
    @Test
    public void testGetters() {
        System.out.println("testGetters");
        DegreeModule instance = new DegreeModule("Object-oriented programming", "otm-4d4c4575-a5ae-427e-a860-2f168ad4e8ba", 5);
        assertEquals("Object-oriented programming", instance.getName());
        assertEquals("otm-4d4c4575-a5ae-427e-a860-2f168ad4e8ba", instance.getGroupId());
        assertEquals(5, instance.getminCredits());
    }

    /**
     * Test of addModules method, of class DegreeModule.
     */
    @Test
    public void testAddModules() {
        System.out.println("addModules");
        String moduleName = "Haha";
        String moduleCode = "otm-4d4c4575-a5ae-427e-a860-2f168ad4e8ba";
        ArrayList<StudyModule> studyModules = null;
        Module module = new Module(moduleName, moduleCode, studyModules);
        DegreeModule instance = new DegreeModule("Object-oriented programming", "otm-4d4c4575-a5ae-427e-a860-2f168ad4e8ba", 5);
        instance.addModules(module);
        assertEquals(1, instance.getModules().size());
    }

    /**
     * Test of readAllDegree method, of class DegreeModule. This test will also
     * test of three different functions
     *
     */
    @Test
    public void testReadAllDegree() throws Exception {
        System.out.println("readAllDegree");
        DegreeModule instance = new DegreeModule("Bachelor's Programme in Science and Engineering", "otm-4d4c4575-a5ae-427e-a860-2f168ad4e8ba", 180);
        instance.readAllDegree();
        assertEquals(2, instance.getModules().size());
    }
}
