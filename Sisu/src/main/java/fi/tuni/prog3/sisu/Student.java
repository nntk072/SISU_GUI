/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.prog3.sisu;

import java.util.ArrayList;

/**
 *
 * @author xblong
 */
public class Student {
    // Store all the course information of each student
    private String email_address;
    private int student_number;
    private DegreeModule degreeModule;
    private ArrayList <FinishCourse> finishCourse;
    // Information getting from Multiple Choices
    private String degreeName;
    private String degreeCode;
    
    private String moduleName;
    private String moduleCode;


    public Student(String email_address, int student_number,DegreeModule degreeModule, ArrayList<FinishCourse> finishCourse, String moduleName, String moduleCode) {
        this.email_address = email_address;
        this.student_number = student_number;
        this.degreeModule = degreeModule;
        this.finishCourse = finishCourse;
        this.moduleName = moduleName;
        this.moduleCode = moduleCode;
    }

    public String getEmail_address() {
        return email_address;
    }

    public int getStudent_number() {
        return student_number;
    }

    public DegreeModule getDegreeModule() {
        return degreeModule;
    }

    public ArrayList<FinishCourse> getFinishCourse() {
        return finishCourse;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public void setDegreeModule(DegreeModule degreeModule) {
        this.degreeModule = degreeModule;
    }

    public void setFinishCourse(ArrayList<FinishCourse> finishCourse) {
        this.finishCourse = finishCourse;
    }    

    // Save the Student into Json files if exists
    public void saveStudent() {
        // TODO implement here
    }
    
        
}
