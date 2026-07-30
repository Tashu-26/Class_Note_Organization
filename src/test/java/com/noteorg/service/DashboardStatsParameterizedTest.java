package com.noteorg.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class DashboardStatsParameterizedTest {

    // ValueSource 
    @ParameterizedTest
    @ValueSource(strings = {
            "Software Engineering",
            "Artificial Intelligence",
            "Web Programming",
            "Computer Network"
    })
    void testSubjectNameIsNotEmpty(String subjectName) {
        assertFalse(subjectName.isBlank());
    }

    // CsvSource 
    @ParameterizedTest
    @CsvSource({
            "Math,Math",
            "Physics,Physics",
            "Java,Java",
            "Database,Database"
    })
    void testSubjectName(String input, String expected) {
        assertEquals(expected, input);
    }

    //  MethodSource 
    static Stream<Arguments> taskTitles() {
        return Stream.of(
                Arguments.of("Complete Assignment", 19),
                Arguments.of("Read Chapter", 12),
                Arguments.of("Practice JUnit", 15),
                Arguments.of("Submit Project", 14)
        );
    }

    @ParameterizedTest
    @MethodSource("taskTitles")
    void testTaskTitleLength(String title, int expectedLength) {
        assertEquals(expectedLength, title.length());
    }

}
