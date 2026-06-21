package com.noteorg.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SubjectTest {

    @Test
    void testSubjectName() {

        Subject subject = new Subject();

        subject.setName("Software Engineering");

        assertEquals(
                "Software Engineering",
                subject.getName()
        );
    }

    @Test
    void testThrowsException() {

        assertThrows(
                NullPointerException.class,
                () -> {
                    String text = null;
                    text.length();
                }
        );
    }
}
