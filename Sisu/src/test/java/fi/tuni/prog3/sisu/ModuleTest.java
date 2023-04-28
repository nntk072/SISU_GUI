/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package fi.tuni.prog3.sisu;

import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.google.gson.JsonArray;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author xblong
 */
public class ModuleTest {

    Module module = null;

    public ModuleTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        String moduleName = "Test1";
        String moduleCode = "Test2";
        ArrayList<StudyModule> studyModules = new ArrayList<>();
        module = new Module(moduleName, moduleCode, studyModules);
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test Getters functions
     */
    @Test
    public void testGetters() {
        assertEquals("Test1", module.getModuleName());
        assertEquals("Test2", module.getModuleCode());
        assertEquals(new ArrayList<StudyModule>(), module.getStudyModules());
    }

    /**
     * Test of addStudyModules method, of class Module.
     */
    @Test
    public void testAddStudyModules() {
        System.out.println("addStudyModules");
        String studyModuleName = "Test1";
        ArrayList<Course> courses = new ArrayList<>();
        JsonArray jsonArray = null;
        StudyModule studyModule = new StudyModule(studyModuleName, jsonArray, courses);
        module.addStudyModules(studyModule);
        assertEquals(1, module.getStudyModules().size());
    }

}
