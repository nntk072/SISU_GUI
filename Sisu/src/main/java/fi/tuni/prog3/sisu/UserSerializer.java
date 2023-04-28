package fi.tuni.prog3.sisu;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class UserSerializer {

    private static final String USER_FILE = "usersStore.json";
    private static final Gson gson = new GsonBuilder().setLenient().create();

    public static void saveUser(User user) throws IOException {
        List<User> users = readUsers();
        users.add(user);
        writeUsers(users);
    }

    public static List<User> readUsers() throws IOException {
        List<User> users = new ArrayList<>();
        File file = new File(USER_FILE);
        if (file.exists()) {
            FileReader fileReader = new FileReader(file);
            User[] usersArray = gson.fromJson(fileReader, User[].class);
            for (User user : usersArray) {
                users.add(user);
            }
            fileReader.close();
        }
        return users;
    }

    private static void writeUsers(List<User> users) throws IOException {
        File file = new File(USER_FILE);
        FileWriter writer = new FileWriter(file);
        String json = gson.toJson(users);
        writer.write(json);
        writer.flush();
        writer.close();
    }
}
