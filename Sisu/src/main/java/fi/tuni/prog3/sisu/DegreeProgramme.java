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

import java.util.TreeMap;

/**
 *
 * @author xblong
 */
public class DegreeProgramme {

    private static TreeMap<String, String> degreeProgramme_list = new TreeMap<>();

    public static TreeMap<String, String> getDegreeProgramme_list() throws MalformedURLException, IOException {

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

}
