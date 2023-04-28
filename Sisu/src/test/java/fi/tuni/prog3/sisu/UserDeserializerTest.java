/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package fi.tuni.prog3.sisu;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author xblong
 */
public class UserDeserializerTest {

    private static final String TEST_USER_JSON = "[{\"firstname\":\"Test\",\"lastname\":\"Test\",\"email\":\"haha@haha.tuni.fi\",\"password\":\"haha\",\"year\":2022,\"studentNumber\":\"11111111\",\"startingDate\":\"27-4-2023\"}]";
    private static final User TEST_USER = new User("Test", "Test", "haha@haha.tuni.fi", "haha", 2022, "11111111", "27-4-2023");

    @Test
    public void testDeserialize() {
        List<User> users = UserDeserializer.deserialize(TEST_USER_JSON);
        assertNotNull(users);
        assertEquals(1, users.size());
        assertEquals(TEST_USER.toString(), users.get(0).toString());
    }

    @Test
    public void testLoadUsers() throws IOException {
        String testFilePath = "test_usersStore.json";
        Files.write(Paths.get(testFilePath), TEST_USER_JSON.getBytes(StandardCharsets.UTF_8));
        List<User> users = UserDeserializer.loadUsers();
        assertNotNull(users);
        assertEquals(1, users.size());
        // TEST_USER and users.get(0) into string
        assertEquals(TEST_USER.toString(), users.get(0).toString());
        Files.delete(Paths.get(testFilePath));
    }

    @Test
    public void testGetUserByEmailAndPassword() throws IOException {
        String testFilePath = "test_usersStore.json";
        Files.write(Paths.get(testFilePath), TEST_USER_JSON.getBytes(StandardCharsets.UTF_8));
        List<User> users = UserDeserializer.loadUsers();
        User user = UserDeserializer.getUserByEmailAndPassword(users, TEST_USER.getEmail(), TEST_USER.getPassword());
        assertNotNull(user);
        // Convert TEST_USER and user into String
        assertEquals(TEST_USER.toString(), user.toString());
    }

    @Test
    public void testCheckEmail() throws IOException {
        String testFilePath = "test_usersStore.json";
        Files.write(Paths.get(testFilePath), TEST_USER_JSON.getBytes(StandardCharsets.UTF_8));
        assertTrue(UserDeserializer.checkEmail(TEST_USER.getEmail()));
        assertFalse(UserDeserializer.checkEmail("nonexistent@haha.tuni.fi"));
        Files.delete(Paths.get(testFilePath));
    }

    @Test
    public void testCheckStudentNumber() throws IOException {
        String testFilePath = "test_usersStore.json";
        Files.write(Paths.get(testFilePath), TEST_USER_JSON.getBytes(StandardCharsets.UTF_8));
        assertTrue(UserDeserializer.checkStudentNumber(TEST_USER.getStudentNumber()));
        assertFalse(UserDeserializer.checkStudentNumber("99999999"));
        Files.delete(Paths.get(testFilePath));
    }
}