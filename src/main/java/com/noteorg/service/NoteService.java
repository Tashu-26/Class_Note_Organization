package com.noteorg.service;

import com.noteorg.model.Note;
import com.noteorg.model.Subject;
import com.noteorg.model.User;
import com.noteorg.repository.NoteRepository;
import com.noteorg.repository.SubjectRepository;
import com.noteorg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private UserRepository userRepository;

    public Map<String, Long> getDashboardStats(User user) {
        Map<String, Long> stats = new HashMap<>();
        stats.put("total_notes", (long) noteRepository.findByUserOrderByIsPinnedDescUpdatedAtDesc(user).size());
        stats.put("total_subjects", (long) subjectRepository.findByUser(user).size());
        stats.put("total_favorites", (long) noteRepository.findByUserAndIsFavoriteTrue(user).size());
        return stats;
    }

    public List<Note> getRecentNotes(User user) {
        return noteRepository.findByUserOrderByIsPinnedDescUpdatedAtDesc(user);
    }

    public List<Subject> getSubjects(User user) {
        return subjectRepository.findByUser(user);
    }

    public Note saveNote(Note note, User user) {
        note.setUser(user);
        return noteRepository.save(note);
    }

    public void deleteNote(Long id, User user) {
        noteRepository.findById(id).ifPresent(note -> {
            if (note.getUser().getId().equals(user.getId())) {
                noteRepository.delete(note);
            }
        });
    }

    public Note getNoteById(Long id, User user) {
        return noteRepository.findById(id)
                .filter(note -> note.getUser().getId().equals(user.getId()))
                .orElse(null);
    }
    public long countBySubject(User user, com.noteorg.model.Subject subject) {
        return noteRepository.findByUserOrderByIsPinnedDescUpdatedAtDesc(user)
                .stream().filter(n -> n.getSubject() != null && n.getSubject().getId().equals(subject.getId())).count();
    }
}
