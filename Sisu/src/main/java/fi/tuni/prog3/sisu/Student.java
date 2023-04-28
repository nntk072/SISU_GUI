package fi.tuni.prog3.sisu;

import java.util.ArrayList;

/**
 * This class stores information about specific student
 * @author xblong
 */
public class Student implements iReadAndWriteToFile {
    // Store all the course information of each student
    private final int studentNumber;
    private DegreeModule degreeModule;
    private final ArrayList <DegreeModule> degreeModules;
    private ArrayList <Course> finishCourse;

    /**
     * Constructor
     * @param student_number
     * @param degreeModules
     * @param finishCourse
     */
    public Student(int student_number, ArrayList <DegreeModule> degreeModules, ArrayList<Course> finishCourse) {
        this.studentNumber = student_number;
        this.degreeModules = degreeModules;
        this.finishCourse = finishCourse;
    }

    /**
     * Get the student number
     * @return student number
     */
    public int getStudent_number() {
        return studentNumber;
    }

    /**
     * Get the degree module lists
     * @return degree module lists
     */
    public ArrayList<DegreeModule> getDegreeModules() {
        return degreeModules;
    }

    /**
     * Get the degree module
     * @return degree module
     */
    public DegreeModule getDegreeModule() {
        return degreeModule;
    }

    /**
     * Get the finished course lists
     * @return finished course lists
     */
    public ArrayList<Course> getFinishCourse() {
        return finishCourse;
    }

    /**
     * Set the degree module
     * @param degreeModule 
     */
    public void setDegreeModule(DegreeModule degreeModule) {
        this.degreeModule = degreeModule;
    }

    /**
     * Set the degree module lists
     * @param finishCourse
     */
    public void setFinishCourse(ArrayList<Course> finishCourse) {
        this.finishCourse = finishCourse;
    }    

    @Override
    public DegreeModule readFromFile(String fileName) throws Exception {
        // Check if fileName is the same as studentNumber
        // if (fileName.equals(Integer.toString(studentNumber))) {
        //     // Read JSON File with filename
        // }
        // else {
        //     throw new Exception("File name is not the same as student number");

        return null;

    }

    @Override
    public boolean writeToFile(String fileName) throws Exception {
        return false;
    }
}
