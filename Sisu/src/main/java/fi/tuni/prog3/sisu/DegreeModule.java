package fi.tuni.prog3.sisu;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;




/**
 * An abstract class for storing information on Modules and Courses.
 */
public class DegreeModule {

    private final String name;
    private final String groupId;
    private final int minCredits;

    private ArrayList<Module> modules;

    /**
     * Constructor for DegreeModule
     *
     * @param name Module/Course name.
     * @param groupId Module/Course groupId.
     * @param minCredits Module/Course minCredits.
     */
    public DegreeModule(String name, String groupId, int minCredits) {
        this.name = name;
        this.groupId = groupId;
        this.minCredits = minCredits;

        modules = new ArrayList<>();

    }

    /**
     * Get the Module/Course name
     *
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns Module/Course groupId
     *
     * @return group id of the Module or Course.
     */
    public String getGroupId() {
        return this.groupId;
    }

    /**
     * Returns the list of modules.
     *
     * @return list of modules.
     */
    public ArrayList<Module> getModules() {
        return this.modules;
    }

    /**
     * Returns the minimum credits of the Module or Course.
     *
     * @return minimum credits of the Module or Course.
     */
    public int getminCredits() {
        return minCredits;
    }

    /**
     * Adds a module to the module list
     *
     * @param module the stored module
     */
    public void addModules(Module module) {
        if (modules == null) {
            this.modules = new ArrayList<>();
        }
        modules.add(module);
    }

    /**
     * Reading all the information of the target degree
     *
     * @throws IOException
     */
    public void readAllDegree() throws IOException {
        readModule(this);
        studyModuleRead();
        courseRead(this);

    }

    /**
     * Reads the modules from the current degree.
     *
     * @param degreeModule Current degree
     * @throws IOException
     */
    public void readModule(DegreeModule degreeModule) throws IOException {

        // Define the JsonObject of the degree 
        JsonObject degreeModuleObject = addModuleObject(degreeModule.getGroupId());

        // Reaching the rules after CompositeRule
        JsonArray moduleArray = new JsonArray();
        JsonArray moduleRules = recursiveDegreeModule(degreeModuleObject, moduleArray);

        // Passing all ComPositeRule, coming to the rules leading to ModuleRule
        JsonArray tempModules = new JsonArray();
        JsonArray modRules = recursiveModules(moduleRules, tempModules);

        // Loops through all the modules in degree
        for (JsonElement module : modRules) {

            // Creating an array for studyModules
            ArrayList<StudyModule> studyModules = new ArrayList<>();

            // Creating a JsonObject of each Module
            String moduleString = module.getAsJsonObject().get("moduleGroupId").getAsString();
            JsonObject moduleObject = addModuleObject(moduleString);

            Module element = new Module(addModuleName(moduleObject), moduleObject.get("id").getAsString(), studyModules);
            // Adds the module to the degree
            degreeModule.addModules(element);
        }
    }

    /**
     * Stores the study modules of the current degree.
     *
     * @throws IOException
     */
    public void studyModuleRead() throws IOException {

        // Loops through all the modules in degree.
        for (Module rule : this.modules) {
            JsonArray studyModuleRules = new JsonArray();
            ArrayList<Course> courseArray = new ArrayList<>();

            // Creating a JsonObject
            String moduleGroupId = rule.getModuleCode();
            JsonObject studyModuleObject = addModuleObject(moduleGroupId);

            studyModuleRules.add(studyModuleObject);

            // Creating a StudyModule object and storing it
            StudyModule studyModule = new StudyModule(addModuleName(studyModuleObject), studyModuleRules, courseArray);
            rule.addStudyModules(studyModule);
        }
    }

    /**
     * Stores the courses in the current degree
     *
     * @param degree Current degree
     * @throws IOException
     */
    public void courseRead(DegreeModule degree) throws IOException {

        // Loops through modules of the degree
        for (Module module : degree.getModules()) {
            for (StudyModule studyModule : module.getStudyModules()) {
                // Leading to the list of course lists of modules according to the rules.
                JsonArray tempCourses = new JsonArray();
                var courses = recursiveCourses(studyModule.getModuleJsonArray(), tempCourses);

                // Loops through courses
                for (JsonElement course : courses) {
                    // Creating a JsonObject
                    String courseGroupID = course.getAsJsonObject().get("courseUnitGroupId").getAsString();
                    JsonObject courseObject = addCourseObject(courseGroupID);

                    String code = courseObject.getAsJsonObject().get("code").getAsString();
                    int credits = courseObject.getAsJsonObject().get("credits").getAsJsonObject().get("min").getAsInt();

                    // Creating a Course object and storing it
                    Course createCourse = new Course(addModuleName(courseObject), code, credits);
                    studyModule.addCourses(createCourse);
                }

            }
        }
    }

    /**
     * Adding Course Object
     *
     * @param courseGroupID
     * @return
     * @throws IOException
     */
    private JsonObject addCourseObject(String courseGroupID) throws IOException {
        String courseURL = createCourseURL(courseGroupID);
        URL url = new URL(courseURL);
        JsonObject courseObject = createModuleObject(url);
        return courseObject;
    }

    /**
     * Creates the URL string for the wanted module URL
     *
     * @param moduleGroupId Group Id for the wanted module
     * @return Complete string
     */
    private String createModuleURL(String moduleGroupId) {

        // Creates the URL depending on the substring in front.
        String URL = "";
        var substring = moduleGroupId.substring(0, 3);

        // Compares the substring
        if (substring.equals("otm")) {
            URL = "https://sis-tuni.funidata.fi/kori/api/modules/" + moduleGroupId;
        } else {
            URL = "https://sis-tuni.funidata.fi/kori/api/modules/by-group-id?groupId=" + moduleGroupId + "&universityId=tuni-university-root-id";
        }

        // Return the URL string
        return URL;
    }

    /**
     * Creates the URL string for the wanted course URL
     *
     * @param courseUnitId wanted courseUnitId for the course URL
     * @return Complete string
     */
    private String createCourseURL(String courseUnitId) {

        // Creates the URL for the courses depending on the substring on front.
        var URL = "";
        var substring = courseUnitId.substring(0, 3);
        if (substring.equals("otm")) {
            URL = "https://sis-tuni.funidata.fi/kori/api/course-units/" + courseUnitId;
        } else {
            URL = "https://sis-tuni.funidata.fi/kori/api/course-units/by-group-id?groupId=" + courseUnitId + "&universityId=tuni-university-root-id";
        }

        // Returns the URL string
        return URL;
    }

    /**
     * Creates a Module object from the API
     *
     * @param url URL to connect to the object
     * @return Wanted object in JSON format
     * @throws IOException
     */
    private JsonObject createModuleObject(URL url) throws IOException {

        // Creating a new JsonObject
        var moduleObject = new JsonObject();
        URLConnection request = url.openConnection();
        JsonElement element = JsonParser.parseReader(new InputStreamReader((InputStream) request.getContent()));

        // Checks if the object is actually an object
        if (!element.isJsonObject()) {
            moduleObject = element.getAsJsonArray().get(0).getAsJsonObject();
        } else {
            moduleObject = element.getAsJsonObject();
        }

        // Returns the object
        return moduleObject;
    }

    /**
     * Recursive function to find modules
     *
     * @param degreeObject Degree where the modules are
     * @param moduleArray Temporary Array to be filled with modules
     * @return Complete list of modules in that current degree
     */
    private JsonArray recursiveDegreeModule(JsonObject degreeObject, JsonArray moduleArray) {

        // Checks the type, if not in wanted type, recurses again.
        if (degreeObject.getAsJsonObject().get("type").getAsString().equals("CompositeRule")) {
            moduleArray = degreeObject.getAsJsonObject().get("rules").getAsJsonArray();
            return moduleArray;
        } else {
            moduleArray = recursiveDegreeModule(degreeObject.getAsJsonObject().get("rule").getAsJsonObject(), moduleArray);
        }

        // Return moduleArray
        return moduleArray;
    }

    /**
     * Recursive function to go through modules.
     *
     * @param modules Modules from the degree
     * @param tempModules Array to store the modules
     * @return tempModules
     */
    private JsonArray recursiveModules(JsonArray modules, JsonArray tempModules) {

        // Looping through modules
        for (JsonElement element : modules) {
            String condition = element.getAsJsonObject().get("type").getAsString();
            // If it is a module, it will be added to the tempModules
            switch (condition) {
                case "ModuleRule":
                    tempModules.add(element.getAsJsonObject());
                    break;
                case "CompositeRule":
                    recursiveModules(element.getAsJsonObject().get("rules").getAsJsonArray(), tempModules);
                    break;
                default:
                    recursiveModules(element.getAsJsonObject().get("rule").getAsJsonArray(), tempModules);
                    break;
            }
        }
        return tempModules;
    }

    /**
     * Checks the validity of a module object
     *
     * @param moduleObject Required module object
     * @return JsonArray of modules.
     */
    private JsonArray checkModuleObject(JsonObject moduleObject) {

        // Checks if the moduleObject has a CompositeRule inside
        if (moduleObject.get("rule").getAsJsonObject().get("type").getAsString().equals("CompositeRule")) {
            return moduleObject.get("rule").getAsJsonObject().get("rules").getAsJsonArray();
        } else {
            return moduleObject.get("rule").getAsJsonObject().get("rule").getAsJsonObject().get("rules").getAsJsonArray();
        }
    }

    /**
     * Recursive function to locate and store courses.
     *
     * @param rules Different module, such as CompositeRule, ModuleRule etc.
     * @param tempCourses Temporary array to store courses.
     * @return Complete list of courses in current degree.
     * @throws IOException
     */
    private JsonArray recursiveCourses(JsonArray rules, JsonArray tempCourses) throws IOException {

        // Loops through the rules
        for (var rule : rules) {

            // If the current rule is a CourseUnit, Course will be added to the tempCourses
            switch (rule.getAsJsonObject().get("type").getAsString()) {
                case "CourseUnitRule":
                    if (!tempCourses.contains(rule.getAsJsonObject())) {
                        tempCourses.add(rule.getAsJsonObject());
                    }
                    break;
                case "CompositeRule":
                    tempCourses = recursiveCourses(rule.getAsJsonObject().get("rules").getAsJsonArray(), tempCourses);
                    break;
                case "ModuleRule":
                    var moduleUrl = createModuleURL(rule.getAsJsonObject().get("moduleGroupId").getAsString());
                    URL url = new URL(moduleUrl);
                    var moduleObject = createModuleObject(url);
                    switch (moduleObject.get("type").getAsString()) {
                        case "ModuleRule":
                            tempCourses = recursiveCourses(moduleObject.getAsJsonObject().getAsJsonArray(), tempCourses);
                            break;
                        case "CompositeRule":
                            tempCourses = recursiveCourses(moduleObject.get("rules").getAsJsonArray(), tempCourses);
                            break;
                        case "StudyModule":
                            tempCourses = recursiveCourses(checkModuleObject(moduleObject), tempCourses);
                            break;
                        default:
                            tempCourses = recursiveCourses(checkModuleObject(moduleObject), tempCourses);
                            break;
                    }
                    break;
                case "AnyModuleRule":
                    continue;
                case "AnyCourseUnitRule":
                    continue;
                default:
                    // Exception checks
                    if (rule.getAsJsonObject().get("rule").getAsJsonObject().get("type").getAsString().equals("CompositeRule")) {
                        tempCourses = recursiveCourses(rule.getAsJsonObject().get("rule").getAsJsonObject().get("rules").getAsJsonArray(), tempCourses);
                    } else {
                        tempCourses = recursiveCourses(rule.getAsJsonObject().get("rule").getAsJsonObject().get("rule").getAsJsonObject().get("rules").getAsJsonArray(), tempCourses);
                    }
                    break;
            }
        }
        return tempCourses;
    }

    /**
     * Returns the inside modules of the Module or Course.
     *
     * @param moduleGroupId
     * @return inside Module
     * @throws IOException
     */
    public JsonObject addModuleObject(String moduleGroupId) throws IOException {
        String studyModuleURL = createModuleURL(moduleGroupId);
        URL url = new URL(studyModuleURL);
        JsonObject studyModuleObject = createModuleObject(url);
        return studyModuleObject;
    }

    /**
     * Get the module name
     *
     * @param jsonModule
     * @return
     */
    public static String addModuleName(JsonObject jsonModule) {
        // Get the module name in the rules
        JsonObject jsonArrayModuleName = jsonModule.get("name").getAsJsonObject();
        //System.out.println(jsonArrayModuleName);
        String enName = "";
        String finName = "";
        String svName = "";
        if (jsonArrayModuleName.has("en")) {
            enName = jsonArrayModuleName.get("en").getAsString();
        }
        if (jsonArrayModuleName.has("fi")) {
            finName = jsonArrayModuleName.get("fi").getAsString();
        }
        if (jsonArrayModuleName.has("sv")) {
            svName = jsonArrayModuleName.get("sv").getAsString();
        }
        String moduleName = "";
        // make the moduleName to be en, if en = null, fin, if fin = null, sv
        if (enName != null && !enName.isEmpty()) {
            moduleName = enName;
        } else if (finName != null && !finName.isEmpty()) {
            moduleName = finName;
        } else if (svName != null && !svName.isEmpty()) {
            moduleName = svName;
        }
        return moduleName;
    }
}
