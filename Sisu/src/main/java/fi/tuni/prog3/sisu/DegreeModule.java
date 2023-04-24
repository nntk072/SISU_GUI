/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
 /*
An abstract supertype Module.
Has concrete subtypes Education, DegreeProgramme, StudyModule and GroupingModule that describe parts of educations and degrees.
Since the coursework will not need to use Education modules, we will not describe it further.
Sisu API offers endpoints for retrieving modules, for example, based on their id’s or groupId’s.
Detailed information about a module with a given id can be retrieved, for example, by request (the id` is “otm-1d25ee85-df98-4c03-b4ff-6cad7b09618b”): https://sis-tuni.funidata.fi/kori/api/modules/otm-1d25ee85-df98-4c03-b4ff-6cad7b09618b
Detailed information about a module with a given groupId can be retrieved, for example, by request (the groupId is “uta-ok-ykoodi-41176”) : https://sis-tuni.funidata.fi/kori/api/modules/by-group-id?groupId=uta-ok-ykoodi-41176&universityId=tuni-university-root-id
Sisu returns module information in JSON format. Each module has the type attribute that specifies the concrete type of the module that is one of the subtypes listed above).

DegreeProgramme.
The root level of a degree structure. Describes, for example, the name and extent (study credits) of the degree.

StudyModule.
A study module, which usually consists of courses or submodules.
Study module completions can be included into a student’s university study record. Study modules have information about study credits, grading, responsible persons etc.

GroupingModule.
A technical substructure that describes an intermediate level in the degree structure. It usually consists of courses or submodules and is superficially similar to a study module.
These differ from study modules mainly in that grouping module completions cannot be recorded into the study record. Hence these do not have information about study credits etc.

CourseUnit.
Describes a course: the code, name, awarded study credits, course description etc.
Note that Sisu does not treat this as a subtybe of Module. Sisu offers separate API endpoints for retrieving information about courses by their id’s, groupId’s etc.
Detailed information about a course with a certain groupId can be retrieved, for example, by request (groupId “uta-ykoodi-47926”): https://sis-tuni.funidata.fi/kori/api/course-units/by-group-id?groupId=uta-ykoodi-47926&universityId=tuni-university-root-id
Modules that directly contain courses refer to courses by their groupId’s.
 */
package fi.tuni.prog3.sisu;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An abstract class for storing information on Modules and Courses.
 */
public abstract class DegreeModule {

    private String name;
    private String id;
    private String groupId;
    private int minCredits;
    private ArrayList<Module> modules;

    /**
     * Returns the modules of the Module or Course.
     *
     * @return modules of the Module or Course.
     */
    public ArrayList<Module> getModules() {
        return modules;
    }

    /**
     * A constructor for initializing the member variables.
     *
     * @param name name of the Module or Course.
     * @param id id of the Module or Course.
     * @param groupId group id of the Module or Course.
     * @param minCredits minimum credits of the Module or Course.
     */
    public DegreeModule(String name, String id, String groupId,
            int minCredits) {

        this.name = name;
        this.id = id;
        this.groupId = groupId;
        this.minCredits = minCredits;
        this.modules = new ArrayList<>();
    }

    /**
     * Returns the name of the Module or Course.
     *
     * @return name of the Module or Course.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the id of the Module or Course.
     *
     * @return id of the Module or Course.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Returns the group id of the Module or Course.
     *
     * @return group id of the Module or Course.
     */
    public String getGroupId() {
        return this.groupId;
    }

    /**
     * Returns the minimum credits of the Module or Course.
     *
     * @return minimum credits of the Module or Course.
     */
    public int getMinCredits() {
        return this.minCredits;
    }
    /*
    private void moduleRead(DegreeModule degree) throws MalformedURLException {
        // Read from https://sis-tuni.funidata.fi/kori/api/modules/otm-1d25ee85-df98-4c03-b4ff-6cad7b09618b
        var id = degree.getId();
        var url_path = "https://sis-tuni.funidata.fi/kori/api/modules/otm-" + id;
        var json = getJsonObjectFromApi(url_path);

        // Read the modules from the json object
        var modules = json.getAsJsonArray("modules");
    }

    @Override
    public JsonObject getJsonObjectFromApi(String urlString) {
        try {
            URL url = new URL(urlString);

            //Read the words from json file from url
            String jsonStrings = new String(url.openStream().readAllBytes(), "UTF-8");

            //Create a json object from the contents
            JsonObject json = JsonParser.parseString(jsonStrings).getAsJsonObject();
            return json;
        } catch (MalformedURLException ex) {
            Logger.getLogger(DegreeModule.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DegreeModule.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    */

}
