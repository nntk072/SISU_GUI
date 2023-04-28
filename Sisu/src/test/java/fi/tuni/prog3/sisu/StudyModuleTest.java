/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package fi.tuni.prog3.sisu;

import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.List;
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
public class StudyModuleTest {
    
    public StudyModuleTest() {
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

    @Test
    public void testAddCourses() {
        System.out.println("addCourses");
        Course course = null;
        String studyModuleName = "Test1";
        ArrayList<Course> courses = new ArrayList<>();
        JsonArray jsonArray = null;
        StudyModule instance = new StudyModule(studyModuleName, jsonArray, courses);
        instance.addCourses(course);
        // check the size of instance list
        assertEquals(1, instance.getCourses().size());
    }
    
}
