package com.noteorg.factory;

import com.noteorg.model.Note;
import com.noteorg.model.Subject;
import com.noteorg.model.User;

public class NoteFactory {

    private NoteFactory() {
    }

  
    public static Note createNote(String title, String content,
                                  Subject subject, User user) {

        Note note = new Note();

        note.setTitle(title);
        note.setContent(content);
        note.setSubject(subject);
        note.setUser(user);

        // Default values
        note.setFavorite(false);
        note.setPinned(false);

        return note;
    }
}
