package com.noteorg.repository;

import com.noteorg.model.Note;
import com.noteorg.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByUserOrderByIsPinnedDescUpdatedAtDesc(User user);
    List<Note> findByUserAndIsFavoriteTrue(User user);
}
