package com.noteorg.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class NoteParameterizedTest {

    @ParameterizedTest
    @ValueSource(strings = {"Math", "Science", "History"})
    void testTitleNotEmpty(String title) {
        assertFalse(title.isEmpty());
    }

    @ParameterizedTest
    @CsvSource({
        "100,100,true",
        "100,200,false",
        "50,50,true"
    })
    void testOwnerCheck(long ownerId, long userId, boolean expected) {
        boolean result = ownerId == userId;
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/note-data.csv", numLinesToSkip = 1)
    void testOwnerCheckFromFile(long ownerId, long userId, boolean expected) {
        boolean result = ownerId == userId;
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("ownerData")
    void testOwnerCheckFromMethod(long ownerId, long userId, boolean expected) {
        boolean result = ownerId == userId;
        assertEquals(expected, result);
    }

    static Stream<Arguments> ownerData() {
        return Stream.of(
            Arguments.of(1, 1, true),
            Arguments.of(1, 2, false),
            Arguments.of(5, 5, true)
        );
    }
}