package com.noteorg.model;

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
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserModelTest {

    private User user;
    private Note note;

    @BeforeAll
    static void initAll() {
        System.out.println("=== User Model Tests Starting ===");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("=== User Model Tests Finished ===");
    }

    @BeforeEach
    void init() {
        user = new User();
        user.setName("Monisha");
        user.setEmail("monisha@university.edu");
        user.setPassword("hashedpassword");
        user.setUniversity("Test University");
        user.setYear("2nd Year");

        note = new Note();
        note.setTitle("Chemistry Notes");
        note.setContent("Chapter 1 content here");
    }

    @AfterEach
    void tearDown() {
        user = null;
        note = null;
    }

    // ✅ AssertNotNull
    @Test
    @Order(1)
    void testUserNotNull() {
        assertNotNull(user, "User object should not be null");
    }

    // ✅ AssertEquals
    @Test
    @Order(2)
    void testUserFieldsCorrect() {
        assertEquals("Monisha", user.getName());
        assertEquals("monisha@university.edu", user.getEmail());
        assertEquals("Test University", user.getUniversity());
        assertEquals("2nd Year", user.getYear());
    }

    // ✅ AssertNotEquals
    @Test
    @Order(3)
    void testDifferentUsersNotEqual() {
        User user2 = new User();
        user2.setEmail("other@university.edu");
        assertNotEquals(user.getEmail(), user2.getEmail());
    }

    // ✅ AssertTrue
    @Test
    @Order(4)
    void testUserEmailContainsAtSign() {
        assertTrue(user.getEmail().contains("@"),
                "Email should contain @ symbol");
    }

    // ✅ AssertFalse
    @Test
    @Order(5)
    void testNoteNotFavoriteByDefault() {
        assertFalse(note.isFavorite(),
                "Note should not be favorite by default");
        assertFalse(note.isPinned(),
                "Note should not be pinned by default");
    }

    // ✅ AssertNull
    @Test
    @Order(6)
    void testNoteSubjectNullByDefault() {
        assertNull(note.getSubject(),
                "Note subject should be null when not assigned");
    }

    // ✅ AssertNotNull
    @Test
    @Order(7)
    void testNoteContentNotNull() {
        assertNotNull(note.getTitle(), "Note title should not be null");
        assertNotNull(note.getContent(), "Note content should not be null");
    }

    // ✅ AssertSame
    @Test
    @Order(8)
    void testSameUserReference() {
        User sameRef = user;
        assertSame(user, sameRef, "Same reference should point to same object");
    }

    // ✅ AssertNotSame
    @Test
    @Order(9)
    void testDifferentUserObjects() {
        User user2 = new User();
        user2.setName("Monisha");
        assertNotSame(user, user2,
                "Two different objects should not be same reference");
    }

    // ✅ AssertArrayEquals
    @Test
    @Order(10)
    void testUserYearOptions() {
        String[] validYears = {"1st Year", "2nd Year", "3rd Year", "4th Year"};
        String[] testYears = {"1st Year", "2nd Year", "3rd Year", "4th Year"};
        assertArrayEquals(validYears, testYears,
                "Year options should match");
    }

    // ✅ AssertTimeout
    @Test
    @Order(11)
    void testUserCreationIsQuick() {
        assertTimeout(java.time.Duration.ofMillis(100), () -> {
            User u = new User();
            u.setName("Quick User");
            u.setEmail("quick@test.com");
            u.setPassword("pass");
        }, "User creation should be very fast");
    }

    // ✅ AssertThrows
    @Test
    @Order(12)
    void testNullPointerOnEmptyUser() {
        User emptyUser = new User();
        assertThrows(NullPointerException.class, () -> {
            String upper = emptyUser.getName().toUpperCase();
        }, "Should throw NullPointerException when name is null");
    }

    // ✅ AssertTrue - password is not empty
    @Test
    @Order(13)
    void testPasswordNotEmpty() {
        assertTrue(user.getPassword() != null
                && !user.getPassword().isEmpty(),
                "Password should not be empty");
    }

    // ✅ Note title length check
    @Test
    @Order(14)
    void testNoteTitleLength() {
        assertTrue(note.getTitle().length() > 0,
                "Note title should have at least 1 character");
        assertTrue(note.getTitle().length() <= 255,
                "Note title should not exceed 255 characters");
    }
}