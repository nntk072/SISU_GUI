/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.prog3.sisu;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.TreeMap;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import static fi.tuni.prog3.sisu.DegreeModule.addModuleName;
import static fi.tuni.prog3.sisu.DegreeModule.addModuleObject;

/**
 *
 * @author xblong
 */
public class DegreeProgramme {

    public DegreeProgramme() {

    }

    private static final TreeMap<String, String> degreeProgrammeTreeMap = new TreeMap<>();
    private static final TreeMap<String, String> degreeModuleTreeMap = new TreeMap<>();

    /**
     * Get the degree programme list from sis-tuni.funidata.fi
     *
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    public static TreeMap<String, String> getDegreeProgramme_list() throws MalformedURLException, IOException {
        degreeProgrammeTreeMap.clear();
        
        String urlString = "https://sis-tuni.funidata.fi/kori/api/module-search?curriculumPeriodId=uta-lvv-2021&universityId=tuni-university-root-id&moduleType=DegreeProgramme&limit=1000";
        URL url = new URL(urlString);
        String jsonStrings = new String(url.openStream().readAllBytes(), "UTF-8");

        //Create a Json Files from the contents
        JsonObject jsonObject = JsonParser.parseString(jsonStrings).getAsJsonObject();
        JsonArray jsonArray = jsonObject.getAsJsonArray("searchResults");
        for (int i = 0; i < jsonArray.size(); i++) {
            String degreeProgrammeName = jsonArray
                    .get(i).getAsJsonObject()
                    .get("name").getAsString();
            String degreeProgrammeGroupId = jsonArray
                    .get(i).getAsJsonObject()
                    .get("groupId").getAsString();
            degreeProgrammeTreeMap.put(degreeProgrammeName, degreeProgrammeGroupId);
        }
        return degreeProgrammeTreeMap;
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
        degreeModuleTreeMap.clear();
        ArrayList<String> degreeModuleList = new ArrayList<>();
        JsonObject jsonDegree = addModuleObject(group_id);

        // Get the moduleGroupID in the rules
        JsonArray jsonArray = jsonDegree.getAsJsonObject("rule").getAsJsonArray("rules");
        if (jsonArray == null) {
            return null;
        }
        // Add the moduleGroupID into degreeModuleList
        for (int i = 0; i < jsonArray.size(); i++) {
            String moduleGroupID = jsonArray.get(i).getAsJsonObject().get("moduleGroupId").getAsString();
            degreeModuleList.add(moduleGroupID);
        }

        if (degreeModuleList.isEmpty()) {
            return null;
        }

        // Add the moduleGroupID into degreeModuleTreeMap
        for (int i = 0; i < degreeModuleList.size(); i++) {
            JsonObject jsonModule = addModuleObject(degreeModuleList.get(i));
            String moduleName = addModuleName(jsonModule);
            String moduleGroupId = degreeModuleList.get(i);

            degreeModuleTreeMap.put(moduleName, moduleGroupId);
        }
        return degreeModuleTreeMap;
    }

    /**
     * Get the course/module targetCredits
     *
     * @param moduleGroupID
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    public static int getCredits(String moduleGroupID) throws MalformedURLException, IOException {
        JsonObject jsonModule = addModuleObject(moduleGroupID);
        //System.out.println(jsonModule);
        int minCredits = jsonModule
                .get("targetCredits").getAsJsonObject()
                .get("min").getAsInt();
        return minCredits;
    }

}
