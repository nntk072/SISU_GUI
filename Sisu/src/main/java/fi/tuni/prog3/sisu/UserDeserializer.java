package fi.tuni.prog3.sisu;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

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

}