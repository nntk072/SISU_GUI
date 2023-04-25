/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.prog3.sisu;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 *
 * @author xblong
 */
public class DegreeProgramme {

    private static TreeMap<String, String> degreeProgramme_list = new TreeMap<>();
    private static TreeMap<String, String> degreeModule_list = new TreeMap<>();

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

    public static TreeMap<String, String> getModule(String group_id) throws MalformedURLException, IOException {
        //clear the degreeModule_list
        degreeModule_list.clear();
        //List for all module link
        ArrayList<String> degreemodule_list = new ArrayList<>();
        //Get all module link
        String urlStringDegree = "https://sis-tuni.funidata.fi/kori/api/modules/by-group-id?groupId=" + group_id + "&universityId=tuni-university-root-id";
        URL urlDegree = new URL(urlStringDegree);

        //Read the words from json file from url (including Finnish letters)
        String jsonStringsDegree = new String(urlDegree.openStream().readAllBytes(), "UTF-8");

        //Create a json object from the contents
        JsonObject jsonDegree = JsonParser.parseString(jsonStringsDegree).getAsJsonArray().get(0).getAsJsonObject();
        // Get the moduleGroupID in the rules
        JsonArray jsonArray = jsonDegree.getAsJsonObject("rule").getAsJsonArray("rules");
        // Check if jsonArray has null or not
        if (jsonArray == null) {
            return null;
        }
        
        // Add the moduleGroupID into degreemodule_list
        for (int i = 0; i < jsonArray.size(); i++) {
            String moduleGroupID = jsonArray.get(i).getAsJsonObject().get("moduleGroupId").getAsString();
            degreemodule_list.add(moduleGroupID);
        }

        // Check of the degreemodule_list variable using for and system.out.println
        // for (int i = 0; i < degreemodule_list.size(); i++) {
        //     System.out.println(degreemodule_list.get(i));
        // }
        // Run through the list of degreemodule_list to find the module name of each module
        // Check if degreemodule_list is empty
        if (degreemodule_list.isEmpty()) {
            return null;
        }
        for (int i = 0; i < degreemodule_list.size(); i++) {
            String urlStringModule = "https://sis-tuni.funidata.fi/kori/api/modules/by-group-id?groupId="
                    + degreemodule_list.get(i) + "&universityId=tuni-university-root-id";
            URL urlModule = new URL(urlStringModule);

            //Read the words from json file from url (including Finnish letters)
            String jsonStringsModule = new String(urlModule.openStream().readAllBytes(), "UTF-8");

            //Create a json object from the contents
            JsonObject jsonModule = JsonParser.parseString(jsonStringsModule).getAsJsonArray().get(0).getAsJsonObject();
            // This is the sample of jsonModule
            // {
            //     "metadata": {
            //         "revision": 32,
            //         "createdBy": "riihelat@tuni.fi",
            //         "createdOn": "2020-12-01T10:20:34.204419",
            //         "lastModifiedBy": "kk96989@tuni.fi",
            //         "lastModifiedOn": "2022-10-13T10:52:26.619676",
            //         "modificationOrdinal": 182596826
            //     },
            //      "name": {
            //          "en": "Natural Sciences and Mathematics"
            //    }
            // }

            // Get the module name in the rules
            JsonObject jsonArrayModuleName = jsonModule.getAsJsonObject("name");
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
            // make the moduleName to be enName/finName/svName if enName and finName and svName != ""
            // enName/finName if svName == ""
            //...

            if (!enName.equals("") && !finName.equals("") && !svName.equals("")) {
                moduleName = enName + "/" + finName + "/" + svName;
            } else if (!enName.equals("") && !finName.equals("") && svName.equals("")) {
                moduleName = enName + "/" + finName;
            } else if (!enName.equals("") && finName.equals("") && !svName.equals("")) {
                moduleName = enName + "/" + svName;
            } else if (enName.equals("") && !finName.equals("") && !svName.equals("")) {
                moduleName = finName + "/" + svName;
            } else if (!enName.equals("") && finName.equals("") && svName.equals("")) {
                moduleName = enName;
            } else if (enName.equals("") && !finName.equals("") && svName.equals("")) {
                moduleName = finName;
            } else if (enName.equals("") && finName.equals("") && !svName.equals("")) {
                moduleName = svName;
            }
            

            // Add the module name and moduleGroupID into degreeModule_list
            String moduleGroupId = degreemodule_list.get(i);
            degreeModule_list.put(moduleName, moduleGroupId);
        }
    return degreeModule_list ;
    }
}
