/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.prog3.sisu;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.TreeMap;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
//import static fi.tuni.prog3.sisu.DegreeModule.addModuleObject;
//import static fi.tuni.prog3.sisu.DegreeModule.addModuleName;
import java.util.ArrayList;

/**
 *
 * @author xblong
 */
public class DegreeProgramme {

    public DegreeProgramme() {

    }

    private static final TreeMap<String, String> degreeProgramme_list = new TreeMap<>();
    private static final TreeMap<String, String> degreeModule_list = new TreeMap<>();


    /**
     * Get the degree programme list from sis-tuni.funidata.fi
     *
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    public static TreeMap<String, String> getDegreeProgramme_list() throws MalformedURLException, IOException {
        //Clear the degreeprogramme_list
        degreeProgramme_list.clear();
        String urlString = "https://sis-tuni.funidata.fi/kori/api/module-search?curriculumPeriodId=uta-lvv-2021&universityId=tuni-university-root-id&moduleType=DegreeProgramme&limit=1000";
        URL url = new URL(urlString);
        //Read the words from json file from url (including Finnish letters)
        String jsonStrings = new String(url.openStream().readAllBytes(), "UTF-8");

        //Create a json object from the contents
        JsonObject json = JsonParser.parseString(jsonStrings).getAsJsonObject();
        JsonArray jsonArray = json.getAsJsonArray("searchResults");
        for (int i = 0; i < jsonArray.size(); i++) {

            String degreeProgrammeName = jsonArray.get(i).getAsJsonObject().get("name").getAsString();
            String degreeProgrammeGroupId = jsonArray.get(i).getAsJsonObject().get("groupId").getAsString();
            degreeProgramme_list.put(degreeProgrammeName, degreeProgrammeGroupId);
        }
        return degreeProgramme_list;
    }

    /**
     * Get the module list from sis-tuni.funidata.fi of the degreeProgramme
     *
     * @param group_id
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    public static TreeMap<String, String> getModule(String group_id) throws MalformedURLException, IOException {
        // Checking if are there any tracks/modules in the degree        
        degreeModule_list.clear();
        //List for all module link
        ArrayList<String> degreemodule_list = new ArrayList<>();
        JsonObject jsonDegree = addModuleObject(group_id);

        // Get the moduleGroupID in the rules
        JsonArray jsonArray = jsonDegree.getAsJsonObject("rule").getAsJsonArray("rules");
        if (jsonArray == null) {
            return null;
        }
        // Add the moduleGroupID into degreemodule_list
        for (int i = 0; i < jsonArray.size(); i++) {
            String moduleGroupID = jsonArray.get(i).getAsJsonObject().get("moduleGroupId").getAsString();
            degreemodule_list.add(moduleGroupID);
        }
        if (degreemodule_list.isEmpty()) {
            return null;
        }
        for (int i = 0; i < degreemodule_list.size(); i++) {
            JsonObject jsonModule = addModuleObject(degreemodule_list.get(i));
            String moduleName = addModuleName(jsonModule);

            // Add the module name and moduleGroupID into degreeModule_list
            String moduleGroupId = degreemodule_list.get(i);
            degreeModule_list.put(moduleName, moduleGroupId);
        }
        return degreeModule_list;
    }

    //Get the minCredits of the modules
    public static int getCredits(String moduleGroupID) throws MalformedURLException, IOException {
        JsonObject jsonModule = addModuleObject(moduleGroupID);
        //System.out.println(jsonModule);
        int minCredits = jsonModule
            .get("targetCredits").getAsJsonObject()
            .get("min").getAsInt();
        return minCredits;
    }
    public static JsonObject addModuleObject(String module_id) throws IOException {
        String urlString = "https://sis-tuni.funidata.fi/kori/api/modules/by-group-id?groupId="
                + module_id + "&universityId=tuni-university-root-id";
        URL url = new URL(urlString);
        String jsonStrings = new String(url.openStream().readAllBytes(), "UTF-8");
        //System.out.println(jsonStrings);
        JsonObject jsonModule = JsonParser.parseString(jsonStrings).getAsJsonArray().get(0).getAsJsonObject();
        return jsonModule;
    }
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
