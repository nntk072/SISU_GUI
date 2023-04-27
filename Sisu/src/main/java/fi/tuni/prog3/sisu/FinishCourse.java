package fi.tuni.prog3.sisu;

import java.util.ArrayList;

/**
 * This class implements the FinishCourse
 *
 * @author xblong
 */
public class FinishCourse {

    private String courseName;
    private String courseCode;
    private int courseCredit;
    private ArrayList<String> courseList;
    //private int courseGrade;

    public FinishCourse() {
    }

    /**
     * Constructor
     *
     * @param courseName
     * @param courseList
     * @param courseCredit
     */
    public FinishCourse(String courseName, ArrayList<String> courseList, int courseCredit) {
        this.courseName = courseName;
        this.courseCredit = courseCredit;
        this.courseList = courseList;
    }

    /**
     * Get courseName
     *
     * @return courseName
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Get courseCode
     *
     * @return coursecode
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * Get courseCredit
     *
     * @return courseCredit
     */
    public int getCourseCredit() {
        return courseCredit;
    }

    // public int getCourseGrade() {
    //     return courseGrade;
    // }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    // public void setCourseGrade(int courseGrade) {
    //     this.courseGrade = courseGrade;
    // }
}
