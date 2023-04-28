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

/**
 *
 * @author xblong
 */
public class DegreeProgramme extends DegreeModule{

    public DegreeProgramme(String name, String groupId, int minCredits) {
     super(name, groupId, minCredits);
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
     * Get the course/module targetCredits
     *
     * @param moduleGroupID
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    public static int getCredits(String moduleGroupID) throws MalformedURLException, IOException {
        JsonObject jsonModule = addModuleObj(moduleGroupID);
        //System.out.println(jsonModule);
        int minCredits = jsonModule
                .get("targetCredits").getAsJsonObject()
                .get("min").getAsInt();
        return minCredits;
    }

    /**
     * Returns the inside modules of the Module or Course.
     *
     * @param module_id
     * @return inside Module
     * @throws UnsupportedEncodingException
     * @throws IOException
     */

   
    public static JsonObject addModuleObj(String module_id) throws IOException {
        //String urlString = checkLink(module_id);
        String urlString = "https://sis-tuni.funidata.fi/kori/api/modules/by-group-id?groupId="
                + module_id + "&universityId=tuni-university-root-id";
        URL url = new URL(urlString);

        String jsonStrings = new String(url.openStream().readAllBytes(), "UTF-8");
        //System.out.println(jsonStrings);
        System.out.println(module_id);
        System.out.println(jsonStrings);

        //JsonObject jsonModule = JsonParser.parseString(jsonStrings).getAsJsonArray().get(0).getAsJsonObject();
        // Check if JsonObject jsonModule = JsonParser.parseString(jsonStrings).getAsJsonArray().get(0).getAsJsonObject(); success, if not moduleObject = element.getAsJsonObject();
        JsonObject jsonModule = null;
        try {
            jsonModule = JsonParser.parseString(jsonStrings).getAsJsonArray().get(0).getAsJsonObject();
        } catch (Exception e) {
            jsonModule = null;
        }
        return jsonModule;
    }

}
