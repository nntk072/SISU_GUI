package fi.tuni.prog3.sisu;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import fi.tuni.prog3.sisu.Module;

/**
 * An abstract class for storing information on Modules and Courses.
 */
public class DegreeModule {

    private String name;
    private String groupId;
    private int minCredits;
    private ArrayList<Module> modules;

    /**
     * Constructor for DegreeModule
     *
     * @param name Module/Course name.
     * @param groupId Module/Course groupId.
     * @param minCredits Module/Course minCredits.
     */
    public DegreeModule(String name, String groupId,
            int minCredits) {
        this.name = name;
        this.groupId = groupId;
        this.minCredits = minCredits;
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
        return modules;
    }

    /**
     * Sets the name of the Module or Course.
     *
     * @param name name of the Module or Course.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the group id of the Module or Course.
     *
     * @param groupId group id of the Module or Course.
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /**
     * Sets the minimum credits of the Module or Course.
     *
     * @param minCredits minimum credits of the Module or Course.
     */
    public void setMinCredits(int minCredits) {
        this.minCredits = minCredits;
    }

    /**
     * Sets the list of modules.
     *
     * @param modules list of modules.
     */
    public void setModules(ArrayList<Module> modules) {
        this.modules = modules;
    }

    /**
     * Returns the minimum credits of the Module or Course.
     *
     * @return minimum credits of the Module or Course.
     */
    public int getMinCredits() {
        return this.minCredits;
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
        moduleRead(this);
        studyModuleRead();
        courseRead(this);
    }

    /**
     * Reads the modules from the current degree.
     *
     * @param degree Current degree
     * @throws IOException
     */
    public void moduleRead(DegreeModule degreeModule) throws IOException {
        // Define the JsonObject of the degree 
        String moduleGroupId = degreeModule.getGroupId();
        JsonObject degreeModuleObject = addModuleObject(moduleGroupId);

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

        for (Module module : degree.getModules()) {
            for (StudyModule studyModule : module.getStudyModules()) {
                // Leading to the list of course lists of modules according to the rules.
                JsonArray tempCourses = new JsonArray();
                JsonArray courses = recursiveCourses(studyModule.getModuleJsonArray(), tempCourses);

                // Loops through courses
                for (JsonElement course : courses) {
                    // Creating a JsonObject
                    // Add all elements for the Course
                    String courseGroupID = course.getAsJsonObject().get("courseUnitGroupId").getAsString();
                    JsonObject courseObject = addCourseObject(courseGroupID);

                    String code = courseObject.getAsJsonObject().get("code").getAsString();
                    int minCredits = courseObject.getAsJsonObject().get("credits").getAsJsonObject().get("min").getAsInt();

                    // Creating a Course object and storing it
                    Course createCourse = new Course(addModuleName(courseObject), code, minCredits);
                    //Course createCourse = new Course(addModuleName(courseObject), courseObject.getAsJsonObject().get("code").getAsString(), courseObject.getAsJsonObject().get("credits").getAsJsonObject().get("min").getAsInt());
                    studyModule.setCourses(createCourse);
                }
            }
        }
    }

    /**
     * Returns the inside modules of the Module or Course.
     *
     * @param module_id
     * @return inside Module
     * @throws UnsupportedEncodingException
     * @throws IOException
     */
    public static JsonObject addModuleObject(String module_id) throws IOException {
        String urlString = "https://sis-tuni.funidata.fi/kori/api/modules/by-group-id?groupId="
                + module_id + "&universityId=tuni-university-root-id";
        URL url = new URL(urlString);
        String jsonStrings = new String(url.openStream().readAllBytes(), "UTF-8");
        System.out.println(jsonStrings);
        System.out.println(module_id);
        if (jsonStrings.equals("[]")) {

            return JsonParser.parseString(jsonStrings).getAsJsonObject();
        }
        JsonObject jsonModule = JsonParser.parseString(jsonStrings).getAsJsonArray().get(0).getAsJsonObject();
        return jsonModule;
    }

    /**
     * Recursive function to find modules
     *
     * @param degreeObject Degree where the modules are
     * @param moduleArray Temporary Array to be filled with modules
     * @return Complete list of modules in that current degree
     */
    public JsonArray recursiveDegreeModule(JsonObject degreeObject, JsonArray moduleArray) {
        // Checks the type, if not in wanted type, recurses again.
        if (degreeObject
                .getAsJsonObject()
                .get("type").getAsString()
                .equals("CompositeRule")) {
            moduleArray = degreeObject
                    .getAsJsonObject()
                    .get("rules").getAsJsonArray();
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
    public JsonArray recursiveModules(JsonArray modules, JsonArray tempModules) {
        // Looping through modules
        for (JsonElement element : modules) {
            String condition = element.getAsJsonObject().get("type").getAsString();
            // ModuleRule case
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
    public JsonArray checkModuleObject(JsonObject moduleObject) {
        // Checks if the moduleObject has a CompositeRule inside
        if (moduleObject.get("rule")
                .getAsJsonObject().get("type").getAsString()
                .equals("CompositeRule")) {
            return moduleObject
                    .get("rule").getAsJsonObject()
                    .get("rules").getAsJsonArray();
        } else {
            return moduleObject
                    .get("rule").getAsJsonObject()
                    .get("rule").getAsJsonObject()
                    .get("rules").getAsJsonArray();
        }
    }

    public void addDegreeModule(Module degreeModule) {
        if (modules == null) {
            this.modules = new ArrayList<>();
        }
        modules.add(degreeModule);
    }

    /**
     * Returns the Course JsonObject
     *
     * @param moduleGroupId
     * @return
     * @throws java.net.MalformedURLException
     * @throws java.io.IOException
     */
    public static JsonObject addCourseObject(String moduleGroupId) throws MalformedURLException, IOException {
        String urlString = "https://sis-tuni.funidata.fi/kori/api/course-units/by-group-id?groupId="
                + moduleGroupId + "&universityId=tuni-university-root-id";
        URL url = new URL(urlString);
        String jsonStrings = new String(url.openStream().readAllBytes(), "UTF-8");
        System.out.println(moduleGroupId);
        System.out.println(jsonStrings);

        // Checks if the object is actually an object
        if (jsonStrings.equals("[]")) {
            return JsonParser.parseString(jsonStrings).getAsJsonObject();
        }
        JsonObject jsonCourse = JsonParser.parseString(jsonStrings).getAsJsonArray().get(0).getAsJsonObject();
        return jsonCourse;
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

    /**
     * Recursive function to locate and store courses.
     *
     * @param rules Different module, such as CompositeRule, ModuleRule etc.
     * @param tempCourses Temporary array to store courses.
     * @return Complete list of courses in current degree.
     * @throws IOException
     */
    public JsonArray recursiveCourses(JsonArray rules, JsonArray tempCourses) throws IOException {
        // Loops through the rules
        for (JsonElement rule : rules) {

            // If the current rule is a CourseUnit, Course will be added to the tempCourses
            if (rule.getAsJsonObject().get("type").getAsString().equals("CourseUnitRule")) {
                if (!tempCourses.contains(rule.getAsJsonObject())) {
                    tempCourses.add(rule.getAsJsonObject());
                }
                // If the rule is a CompositeRule, recursive function will take its rules as an array.
            } else if (rule.getAsJsonObject().get("type").getAsString().equals("CompositeRule")) {
                tempCourses = recursiveCourses(rule.getAsJsonObject().get("rules").getAsJsonArray(), tempCourses);
                // If the rule is ModuleRule, it will be created as an object and used recursion again
            } else if (rule.getAsJsonObject().get("type").getAsString().equals("ModuleRule")) {
                String moduleGroupId = rule.getAsJsonObject().get("moduleGroupId").getAsString();
                JsonObject moduleObject = addModuleObject(moduleGroupId);
                // Same type of exception testing
                switch (moduleObject.get("type").getAsString()) {
                    case "ModuleRule":
                        tempCourses = recursiveCourses(moduleObject.getAsJsonObject().getAsJsonArray(), tempCourses);
                        break;
                    // AnyModule rules and AnyCourseUnits won't be processed.
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
            } else if (rule.getAsJsonObject().get("type").getAsString().equals("AnyModuleRule")) {
            } else if (rule.getAsJsonObject().get("type").getAsString().equals("AnyCourseUnitRule")) {
            } else {
                if (rule.getAsJsonObject().get("rule").getAsJsonObject().get("type").getAsString().equals("CompositeRule")) {
                    tempCourses = recursiveCourses(rule.getAsJsonObject().get("rule").getAsJsonObject().get("rules").getAsJsonArray(), tempCourses);
                } else {
                    tempCourses = recursiveCourses(rule.getAsJsonObject().get("rule").getAsJsonObject().get("rule").getAsJsonObject().get("rules").getAsJsonArray(), tempCourses);
                }
            }
        }
        return tempCourses;
    }

}
