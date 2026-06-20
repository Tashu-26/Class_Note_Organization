package com.noteorg.service;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.noteorg.model.Note;
import com.noteorg.model.User;
import com.noteorg.repository.UserRepository;

@SpringBootTest
public class NoteServiceTest {

    @Autowired
    private NoteService noteService;

    @Autowired
    private UserRepository userRepository;

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
        user.setName("Test User");
        user.setEmail("test@noteorg.com");
        user.setPassword("123456");

        user = userRepository.save(user);

        note = new Note();
        note.setTitle("Test Note");
        note.setContent("Test Content");
    }


    @AfterEach
    void tearDown() {
        note = null;
        user = null;

        System.out.println("Test completed");
    }


    @Test
    void testUserNotNull() {

        assertNotNull(user);
    }


    @Test
    void testUserDetails() {

        assertEquals("Test User", user.getName());
        assertEquals("test@noteorg.com", user.getEmail());

        assertNotEquals("Wrong User", user.getName());
    }


    @Test
    void testNoteSave() {

        Note saved = noteService.saveNote(note, user);

        assertNotNull(saved);
        assertTrue(saved.getId() > 0);
    }


    @Test
    void testFavoriteStatus() {

        Note saved = noteService.saveNote(note, user);

        assertFalse(saved.isFavorite());

        saved.setFavorite(true);

        assertTrue(saved.isFavorite());
    }


    @Test
    void testSubjectNullAndNotNull() {

        assertNull(note.getSubject());
    }


    @Test
    void testSameAndNotSame() {

        User u1 = user;
        User u2 = u1;

        User u3 = new User();

        assertSame(u1, u2);
        assertNotSame(u1, u3);
    }


    @Test
    void testArrayEquals() {

        String[] expected = {
                "Title",
                "Content"
        };

        String[] actual = {
                note.getTitle(),
                note.getContent()
        };

        assertArrayEquals(expected, actual);
    }


    @Test
    void testTimeout() {

        assertTimeout(
                Duration.ofSeconds(1),
                () -> {
                    noteService.saveNote(note, user);
                }
        );
    }


    @Test
    void testNotThrow() {

        assertDoesNotThrow(() -> {

            noteService.saveNote(note, user);

        });
    }
}