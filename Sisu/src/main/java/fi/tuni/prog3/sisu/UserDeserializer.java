package fi.tuni.prog3.sisu;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;

public class UserDeserializer {

    private static final Gson gson = new GsonBuilder().create();

    public static List<User> deserialize(String json) {
        try {
            User[] users = gson.fromJson(json, User[].class);
            return Arrays.asList(users);
        } catch (JsonParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String readJsonFromFile(String filePath) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(filePath));
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public static List<User> loadUsers() throws IOException {
        String json = readJsonFromFile("usersStore.json");
        List<User> users = Arrays.asList(gson.fromJson(json, User[].class));
        String jsonString = gson.toJson(users);
        return deserialize(jsonString);
    }

    public static User getUserByEmailAndPassword(List<User> users, String email, String password) {
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null; // if no matching user is found
    }

    public static boolean checkEmail(String email) throws IOException {
        // check email exists in usersStone.json using loadUsers and for loop
        String json = readJsonFromFile("usersStore.json");
        List<User> users = Arrays.asList(gson.fromJson(json, User[].class));

        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;

    }

    public static boolean checkStudentNumber(String text) throws IOException {
        // check are there any identical student number in usersStore.json
        String json = readJsonFromFile("usersStore.json");
        List<User> users = Arrays.asList(gson.fromJson(json, User[].class));

        for (User user : users) {
            if (user.getStudentNumber().equals(text)) {
                return true;
            }
        }
        return false;
    }
}
