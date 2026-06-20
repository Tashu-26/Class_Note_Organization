package com.noteorg.model;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserModelTest {

    private User user;
    private Note note;


    @BeforeAll
    static void beforeAll() {
        System.out.println("Before All");
    }


    @AfterAll
    static void afterAll() {
        System.out.println("After All");
    }


    @BeforeEach
    void setUp() {

        user = new User();
        user.setName("Monisha");
        user.setEmail("monisha@test.com");
        user.setPassword("12345");
        user.setUniversity("Test University");
        user.setYear("3rd Year");


        note = new Note();
        note.setTitle("Chemistry Notes");
        note.setContent("Chapter 1");
    }


    @AfterEach
    void tearDown() {

        user = null;
        note = null;
    }


    @Test
    void testUserNotNull() {

        assertNotNull(user);
    }


    @Test
    void testUserDetails() {

        assertEquals("Monisha", user.getName());
        assertEquals("monisha@test.com", user.getEmail());

        assertNotEquals("wrong@test.com", user.getEmail());
    }


    @Test
    void testEmailValid() {

        assertTrue(user.getEmail().contains("@"));

    }


    @Test
    void testNoteStatus() {

        assertFalse(note.isFavorite());
        assertFalse(note.isPinned());

    }


    @Test
    void testNullAndNotNull() {

        assertNull(note.getSubject());

        assertNotNull(note.getTitle());
        assertNotNull(note.getContent());

    }


    @Test
    void testSameAndNotSame() {

        User u1 = user;
        User u2 = new User();

        assertSame(u1, user);
        assertNotSame(u1, u2);

    }


    @Test
    void testArrayEquals() {

        String[] expected = {
                "1st Year",
                "2nd Year",
                "3rd Year"
        };

        String[] actual = {
                "1st Year",
                "2nd Year",
                "3rd Year"
        };


        assertArrayEquals(expected, actual);

    }


    @Test
    void testPassword() {

        assertTrue(
                user.getPassword() != null &&
                !user.getPassword().isEmpty()
        );

    }


    @Test
    void testTimeout() {

        assertTimeout(
                Duration.ofSeconds(1),
                () -> {
                    User u = new User();
                    u.setName("Fast User");
                }
        );

    }


    @Test
    void testThrows() {

        User empty = new User();

        assertThrows(
                NullPointerException.class,
                () -> empty.getName().toUpperCase()
        );

    }
}