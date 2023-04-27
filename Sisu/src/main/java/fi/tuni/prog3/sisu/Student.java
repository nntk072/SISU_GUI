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
    private ArrayList <DegreeModule> degreeModules;
    private ArrayList <FinishCourse> finishCourse;

    /**
     * Constructor
     * @param student_number
     * @param degreeModules
     * @param finishCourse
     */
    public Student(int student_number, ArrayList <DegreeModule> degreeModules, ArrayList<FinishCourse> finishCourse) {
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
    public ArrayList<FinishCourse> getFinishCourse() {
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
     * @param degreeModules 
     */
    public void setFinishCourse(ArrayList<FinishCourse> finishCourse) {
        this.finishCourse = finishCourse;
    }    

    @Override
    public boolean readFromFile(String fileName) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean writeToFile(String fileName) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
        
}
