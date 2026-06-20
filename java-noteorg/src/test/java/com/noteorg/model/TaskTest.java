package com.noteorg.model;

import org.junit.jupiter.api.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    private Task task;

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
        task = new Task();
    }

    @AfterEach
    void tearDown() {
        task = null;
    }

    @Test
    void testTitle() {
        task.setTitle("Assignment");

        assertEquals("Assignment", task.getTitle());
        assertNotEquals("Quiz", task.getTitle());
    }

    @Test
    void testCompletedStatus() {
        task.setCompleted(true);

        assertTrue(task.isCompleted());

        task.setCompleted(false);

        assertFalse(task.isCompleted());
    }

    @Test
    void testNullAndNotNull() {
        assertNull(task.getDescription());

        task.setDescription("Java Project");

        assertNotNull(task.getDescription());
    }

    @Test
    void testSameAndNotSame() {
        String p1 = "high";
        String p2 = p1;
        String p3 = new String("high");

        assertSame(p1, p2);
        assertNotSame(p1, p3);
    }

    @Test
    void testArrayEquals() {
        String[] expected = {"Low", "Medium", "High"};
        String[] actual = {"Low", "Medium", "High"};

        assertArrayEquals(expected, actual);
    }

    @Test
    void testTimeout() {
        assertTimeout(
                java.time.Duration.ofSeconds(1),
                () -> {
                    Thread.sleep(100);
                }
        );
    }

    @Test
    void testNotThrow() {
        assertDoesNotThrow(() -> {
            task.setDueDate(LocalDate.now());
        });
    }
}
