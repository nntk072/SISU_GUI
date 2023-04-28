package fi.tuni.prog3.sisu;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;

/**
 * This class stores information about individual study module
 */
public class StudyModule {

    private final String studyModuleName;
    private final JsonArray moduleJsonArray;
    private final ArrayList<Course> courses;

    /**
     * Constructor Precondition: courses is not empty Post-condition: courses
     * don't change
     *
     * @param moduleName Name of the study module
     * @param moduleJsonArray JsonArray of module rules
     * @param courses ArrayList of courses
     */
    public StudyModule(String moduleName, JsonArray moduleJsonArray, ArrayList<Course> courses) {
        this.studyModuleName = moduleName;
        this.moduleJsonArray = moduleJsonArray;
        this.courses = courses;
    }

    /**
     * Gets the name of the study module
     *
     * @return name of the study module
     */
    public String getStudyModuleName() {
        return studyModuleName;
    }

    /**
     * Gets the list of the courses
     *
     * @return ArrayList of courses
     */
    public List<Course> getCourses() {
        return courses;
    }

    /**
     * Gets the JsonArray of the module rules
     *
     * @return JsonArray of module rules
     */
    public JsonArray getModuleJsonArray() {
        return moduleJsonArray;
    }

    /**
     * Adds the wanted course to the courses ArrayList
     *
     * @param course wanted course to be added
     */
    public void addCourses(Course course) {
        this.courses.add(course);
    }

}
