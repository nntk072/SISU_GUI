package fi.tuni.prog3.sisu;

/**
 * This class stores information about the Course
 * @author xblong
 */
public class Course {
    private final String courseName;
    private final String courseCode;
    private final int credits;

    /**
     * Constructor
     * @param courseName
     * @param courseCode
     * @param credits
     */
    public Course(String courseName, String courseCode, int credits) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.credits = credits;
    }


    /**
     * Gets the course name
     * @return course name
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Gets the course code
     * @return course code
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * Gets the course credits
     * @return course credits
     */
    public int getCredits() {
        return credits;
    }
}
