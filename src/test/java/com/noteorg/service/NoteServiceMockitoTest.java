package com.noteorg.service;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.noteorg.model.Note;
import com.noteorg.model.User;
import com.noteorg.repository.NoteRepository;
import com.noteorg.repository.SubjectRepository;
import com.noteorg.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class NoteServiceMockitoTest {

    @Mock
    private NoteRepository noteRepository;

    @Mock
    private SubjectRepository subjectRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private NoteService noteService;

    private User user;
    private Note note;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);

        note = new Note();
        note.setId(10L);
        note.setTitle("Test Note");
        note.setUser(user);
    }

    @Test
    void testSaveNote() {

        when(noteRepository.save(note)).thenReturn(note);

        Note result = noteService.saveNote(note, user);

        assertNotNull(result);
        assertEquals("Test Note", result.getTitle());

        verify(noteRepository).save(note);
    }
    @Test
    void testGetNoteById() {

        when(noteRepository.findById(10L)).thenReturn(Optional.of(note));

        Note result = noteService.getNoteById(10L, user);

        assertNotNull(result);
        assertEquals(10L, result.getId());
    }
    @Test
    void testDeleteNote() {

        when(noteRepository.findById(10L)).thenReturn(Optional.of(note));

        noteService.deleteNote(10L, user);

        verify(noteRepository).delete(note);
    }
}