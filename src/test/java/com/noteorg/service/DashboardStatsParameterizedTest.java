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

    static Stream<String> subjectColors() {

        return Stream.of(
                "purple",
                "blue",
                "green",
                "red"
        );
    }

    @ParameterizedTest
    @MethodSource("subjectColors")
    void testSubjectColor(String color) {

        Subject subject = new Subject();

        subject.setColor(color);

        assertEquals(color, subject.getColor());
    }
}
