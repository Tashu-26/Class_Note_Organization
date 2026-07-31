package com.noteorg.service;

import com.noteorg.model.Subject;
import com.noteorg.model.Task;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class DashboardStatsParameterizedTest {

    // Test Subject names using @ValueSource
    @ParameterizedTest
    @ValueSource(strings = {
            "Software Engineering",
            "Computer Network",
            "Artificial Intelligence",
            "Web Programming"
    })
    void testSubjectName(String name) {

        Subject subject = new Subject();

        subject.setName(name);

        assertEquals(name, subject.getName());
        assertFalse(subject.getName().isBlank());
    }

    // Test Task title and completion status using @CsvSource
    @ParameterizedTest
    @CsvSource({
            "Assignment,false",
            "Presentation,true",
            "Project,false",
            "Lab Report,true"
    })
    void testTaskStatus(String title, boolean completed) {

        Task task = new Task();

        task.setTitle(title);
        task.setCompleted(completed);

        assertEquals(title, task.getTitle());
        assertEquals(completed, task.isCompleted());
    }

    // Method source for Subject colors
    static Stream<String> subjectColors() {

        return Stream.of(
                "purple",
                "blue",
                "green",
                "red"
        );
    }

    // Test Subject color using @MethodSource
    @ParameterizedTest
    @MethodSource("subjectColors")
    void testSubjectColor(String color) {

        Subject subject = new Subject();

        subject.setColor(color);

        assertEquals(color, subject.getColor());
    }
}
