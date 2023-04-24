package fi.tuni.prog3.sisu;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import com.google.gson.GsonBuilder;
//import com.google.gson.Gson;
//
////we use the Gson library to convert the User object to JSON and write it to a file.
//// The FileWriter is set to append mode so that new users are added to the file rather than overwriting it.
//
////Now you can use the saveUser method to store user information in JSON format
//public class UserSerializer {
//    private static final String USER_FILE = "usersStore.json";
////    private static final Gson gson = new Gson();
//    private static final Gson gson = new GsonBuilder().setLenient().create();
//
//    public static void saveUser(User user) throws IOException {
//        File file = new File(USER_FILE);
//        FileWriter writer;
//        if (file.exists()) {
//            writer = new FileWriter(file, true);
//        } else {
//            file.createNewFile();
//            writer = new FileWriter(file);
//        }
//        String json = gson.toJson(user);
//        if (file.length() > 0) {
//            writer.write(",");
//        }
//        writer.write(json);
//        writer.flush();
//        writer.close();
//    }
//}

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

    private static List<User> readUsers() throws IOException {
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
