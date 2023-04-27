/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.prog3.sisu;

/**
 *
 * @author xblong
 */
public class FinishCourse {
    private String courseName;
    private String courseCode;
    private int courseCredit;
    //private int courseGrade;

    public FinishCourse() {
    }

    public FinishCourse(String courseName, String courseCode, int courseCredit) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.courseCredit = courseCredit;
    }
    /*
    public FinishCourse(String courseName, String courseCode, int courseCredit, int courseGrade) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.courseCredit = courseCredit;
        this.courseGrade = courseGrade;
    }*/
    public FinishCourse(String courseName, String courseCode, int courseCredit, int courseGrade) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.courseCredit = courseCredit;
        //this.courseGrade = courseGrade;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public int getCourseCredit() {
        return courseCredit;
    }

    // public int getCourseGrade() {
    //     return courseGrade;
    // }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setCourseCredit(int courseCredit) {
        this.courseCredit = courseCredit;
    }

    // public void setCourseGrade(int courseGrade) {
    //     this.courseGrade = courseGrade;
    // }
}
